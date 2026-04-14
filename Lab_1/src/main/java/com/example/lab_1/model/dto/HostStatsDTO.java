package com.example.lab_1.model.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class HostStatsDTO {
    @Data
    public static class Response {
        private Long id;
        private String name;
        private String surname;
        private int totalRooms;
        private int totalRentedRooms;
        private ConditionDTO.Response condition;
    }
}