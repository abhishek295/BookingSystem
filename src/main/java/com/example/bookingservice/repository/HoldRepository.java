package com.example.bookingservice.repository;

import com.example.bookingservice.entity.Hold;
import com.example.bookingservice.entity.HoldStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HoldRepository extends JpaRepository<Hold, Long> {
    List<Hold> findByStatusAndExpiresAtBefore(HoldStatus status, LocalDateTime time);
    
}
