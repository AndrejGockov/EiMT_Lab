package com.example.lab_1.model.dto;

import com.example.lab_1.model.enums.Category;
import com.example.lab_1.model.enums.Condition;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

public class AccommodationDTO {
    @Data
    public static class Request {
        @NotBlank(message = "Accommodation name is required")
        private String name;

        @NotNull(message = "Category is required")
        private Category category;

        @NotNull(message = "Condition is required")
        private Condition condition;

        @NotNull(message = "Number of rooms is required")
        @Min(value = 1, message = "Number of rooms must be at least 1")
        private Integer numRooms;

        @NotNull(message = "Host ID is required")
        private Long hostId;
    }

    @Data
    public static class Response {
        private Long id;
        private String name;
        private Category category;
        private Condition condition;
        private Boolean rented;
        private Integer numRooms;
        private HostDTO.Response host;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}