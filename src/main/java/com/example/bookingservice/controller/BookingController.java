package com.example.bookingservice.controller;


import com.example.bookingservice.BookingService;
import com.example.bookingservice.dto.HoldRequest;
import com.example.bookingservice.entity.Booking;
import com.example.bookingservice.entity.Hold;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping("/holds")
    public Hold hold(@RequestBody HoldRequest request) {
        return bookingService.createHold(request.getEventId(),
                request.getUserId(), request.getSeats());
    }

    @PostMapping("/bookings/confirm")
    public Booking confirm(@RequestParam Long holdId) {
        return bookingService.confirmBooking(holdId);
    }

    @PostMapping("bookings/cancel")
    public Booking cancel(@RequestParam Long bookingId) {
        return bookingService.cancelBooking(bookingId);
    }
}
