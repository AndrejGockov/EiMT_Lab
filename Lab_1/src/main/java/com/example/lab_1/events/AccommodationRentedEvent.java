package com.example.lab_1.events;

import org.springframework.context.ApplicationEvent;

public class AccommodationRentedEvent extends ApplicationEvent {
    private final Long accommodationId;
    private final String accommodationName;
    private final int remainingRooms;

    public AccommodationRentedEvent(Object source, Long accommodationId, String accommodationName, int remainingRooms) {
        super(source);
        this.accommodationId = accommodationId;
        this.accommodationName = accommodationName;
        this.remainingRooms = remainingRooms;
    }

    public Long getAccommodationId() {
        return accommodationId;
    }

    public String getAccommodationName() {
        return accommodationName;
    }

    public int getRemainingRooms() {
        return remainingRooms;
    }
}