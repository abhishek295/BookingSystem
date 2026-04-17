package com.example.bookingservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import java.time.LocalDate;

@Entity
public class Event {
    //@Id @GeneratedValue
    @Id @GeneratedValue
    private Long id;
    private String name;
    private LocalDate date;
    private int totalSeats;
    private int availableSeats;
    private String location;

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int i) {
        availableSeats = i;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public String getLocation() { return location; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Version // 🔥 optimistic locking
    private int version;
}
