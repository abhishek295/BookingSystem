package com.example.bookingservice.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Booking {
    @Id @GeneratedValue
    private Long bookingId;

    private Long eventId;
    private Long userId;
    private int numOfSeats;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setNumOfSeats(int numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public Long getEventId() {
        return eventId;
    }

    public int getNumOfSeats() {
        return numOfSeats;
    }
}
