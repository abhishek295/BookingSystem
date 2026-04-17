package com.example.bookingservice;

import com.example.bookingservice.entity.*;
import com.example.bookingservice.repository.BookingRepository;
import com.example.bookingservice.repository.EventRepository;
import com.example.bookingservice.repository.HoldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {
    @Autowired
    public EventRepository eventRepository;

    @Autowired
    public HoldRepository holdRepository;

    @Autowired
    public BookingRepository bookingRepository;

    @Transactional
    public Hold createHold(Long eventId, Long userId, int seats) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        if (event.getAvailableSeats() < seats) {
            throw new RuntimeException("Not enough seats");
        }
        event.setAvailableSeats(event.getAvailableSeats()-seats); //subtract seats

        Hold hold = new Hold();
        hold.setEventId(eventId);
        hold.setUserId(userId);
        hold.setSeats(seats);
        hold.setStatus(HoldStatus.Active);
        hold.setExpiresAt(LocalDateTime.now().plusMinutes(5));

        eventRepository.save(event);
        return holdRepository.save(hold);

    }

    @Transactional
    public Booking confirmBooking(Long holdId) {
        Hold hold = holdRepository.findById(holdId)
                .orElseThrow(() -> new RuntimeException("Hold not found"));

        if (hold.getStatus() != HoldStatus.Active || hold.getExpiresTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Hold expired");
        }

        Booking booking = new Booking();
        booking.setEventId(hold.getEventId());
        booking.setUserId(hold.getUserId());
        ;
        booking.setNumOfSeats(hold.getNoOfSeats());
        booking.setStatus(BookingStatus.Confirmed);

        hold.setStatus(HoldStatus.Confirmed);

        return bookingRepository.save(booking);
    }

    @Transactional
    public Booking cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow();

        booking.setStatus(BookingStatus.Cancelled);
        Event event = eventRepository.findById(booking.getEventId()).get();
        event.setAvailableSeats(event.getAvailableSeats()+booking.getNumOfSeats());
        eventRepository.save(event);
        return bookingRepository.save(booking);
    }

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void expireHolds() {
        List<Hold> holds = holdRepository.findByStatusAndExpiresAtBefore(HoldStatus.Active,
                LocalDateTime.now());
        List<Event> events = new ArrayList<>();
        //create a list to update all unreserved holds in event table
        for(Hold hold: holds) {
            hold.setStatus(HoldStatus.Expired);
            Event event = eventRepository.findById(hold.getEventId()).get();
            event.setAvailableSeats(event.getAvailableSeats() + hold.getNoOfSeats());
            events.add(event);
        }

        holdRepository.saveAll(holds);
        eventRepository.saveAll(events);
    }
}
