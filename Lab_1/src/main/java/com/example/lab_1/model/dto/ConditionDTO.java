package com.example.lab_1.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public class ConditionDTO{
    @Data
    public static class Request {
        @NotBlank(message = "num is required")
        private String GoodRooms;

        @NotBlank(message = "bad num is required")
        private String BadRooms;
    }

    @Data
    public static class Response {
        private int GoodRooms;
        private int BadRooms;
    }
}