package com.example.lab_1.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public class CountryDTO {
    @Data
    public static class Request {
        @NotBlank(message = "Country name is required")
        private String name;

        @NotBlank(message = "Continent is required")
        private String continent;
    }

    @Data
    public static class Response {
        private Long id;
        private String name;
        private String continent;
    }
}