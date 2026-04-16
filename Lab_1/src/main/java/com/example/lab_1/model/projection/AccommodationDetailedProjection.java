package com.example.lab_1.model.projection;

import com.example.lab_1.model.enums.Category;

public interface AccommodationDetailedProjection {
    Long getId();
    String getName();
    Category getCategory();
    Integer getNumRooms();
    String getHostFullName();
    String getHostCountryName();
}
