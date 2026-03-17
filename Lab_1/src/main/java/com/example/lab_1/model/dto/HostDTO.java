package com.example.lab_1.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

public class HostDTO {
    @Data
    public static class Request {
        @NotBlank(message = "Host name is required")
        private String name;

        @NotBlank(message = "Host surname is required")
        private String surname;

        @NotNull(message = "Country ID is required")
        private Long countryId;
    }

    @Data
    public static class Response {
        private Long id;
        private String name;
        private String surname;
        private CountryDTO.Response country;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}