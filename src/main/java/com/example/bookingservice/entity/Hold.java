package com.example.bookingservice.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Hold {
    @Id @GeneratedValue
    private long id;

    private Long eventId;
    private Long userId;
    private int noOfSeats;

    @Enumerated(EnumType.STRING)
    private HoldStatus status;
    private LocalDateTime expiresAt;

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setSeats(int seats) {
        this.noOfSeats = seats;
    }

    public void setStatus(HoldStatus status) {
        this.status = status;
    }

    public void setExpiresAt(LocalDateTime localDateTime) {
        this.expiresAt = localDateTime;
    }

    public HoldStatus getStatus() {
        return status;
    }

    public LocalDateTime getExpiresTime() {
        return expiresAt;
    }

    public Long getEventId() {
        return eventId;
    }

    public Long getUserId() {
        return userId;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }
}
