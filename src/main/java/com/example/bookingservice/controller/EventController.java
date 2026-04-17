package com.example.bookingservice.controller;

import com.example.bookingservice.entity.Event;
import com.example.bookingservice.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventRepository eventRepo;

    @PostMapping("/create")
    public Event createEvent(@RequestBody Event event) {
        event.setAvailableSeats(event.getTotalSeats()); // initialize
        return eventRepo.save(event);
    }

    @GetMapping("/get")
    public Event getAvailability(@RequestParam Long eventId) {
        return eventRepo.findEventById(eventId);
    }
}
