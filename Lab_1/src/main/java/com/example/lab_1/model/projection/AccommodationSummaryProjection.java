package com.example.lab_1.model.projection;

import com.example.lab_1.model.enums.Category;

public interface AccommodationSummaryProjection {
    Long getId();
    String getName();
    Category getCategory();
    Integer getNumRooms();
}
