package com.example.bookingservice.dto;

public class HoldRequest {
    public Long eventId;
    public int seats;
    public Long userId;

    public Long getUserId() {
        return userId;
    }

    public int getSeats() {
        return seats;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
