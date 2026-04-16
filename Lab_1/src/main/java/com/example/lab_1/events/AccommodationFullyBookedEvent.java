package com.example.lab_1.events;

import org.springframework.context.ApplicationEvent;

public class AccommodationFullyBookedEvent extends ApplicationEvent {
    private final Long accommodationId;
    private final String accommodationName;

    public AccommodationFullyBookedEvent(Object source, Long accommodationId, String accommodationName) {
        super(source);
        this.accommodationId = accommodationId;
        this.accommodationName = accommodationName;
    }

    public Long getAccommodationId() {
        return accommodationId;
    }

    public String getAccommodationName() {
        return accommodationName;
    }
}