package com.example.lab_1.listeners;

import com.example.lab_1.events.AccommodationFullyBookedEvent;
import com.example.lab_1.events.AccommodationRentedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class AccommodationEventListener {
    @EventListener
    public void handleRentedEvent(AccommodationRentedEvent event) {
        log.info("Accommodation rented: ID={}, Name={}, Remaining rooms={}, Time={}",
                event.getAccommodationId(),
                event.getAccommodationName(),
                event.getRemainingRooms(),
                java.time.LocalDateTime.now());
    }

    @EventListener
    public void handleFullyBookedEvent(AccommodationFullyBookedEvent event) {
        log.warn("Accommodation is now fully booked: ID={}, Name={}, Time={}",
                event.getAccommodationId(),
                event.getAccommodationName(),
                java.time.LocalDateTime.now());
    }
}