package com.example.lab_1.controller;

import com.example.lab_1.model.dto.AccommodationDTO;
import com.example.lab_1.service.AccommodationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodations")
@RequiredArgsConstructor
public class AccommodationController {
    private final AccommodationService accommodationService;

    @GetMapping
    public List<AccommodationDTO.Response> getAllAccommodations() {
        return accommodationService.getAllAccommodations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccommodationDTO.Response> getAccommodationById(@PathVariable Long id) {
        return ResponseEntity.ok(accommodationService.getAccommodationById(id));
    }

    @PostMapping
    public ResponseEntity<AccommodationDTO.Response> createAccommodation(@Valid @RequestBody AccommodationDTO.Request request) {
        return new ResponseEntity<>(accommodationService.createAccommodation(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccommodationDTO.Response> updateAccommodation(@PathVariable Long id, @Valid @RequestBody AccommodationDTO.Request request) {
        return ResponseEntity.ok(accommodationService.updateAccommodation(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccommodation(@PathVariable Long id) {
        accommodationService.deleteAccommodation(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/rent")
    public ResponseEntity<AccommodationDTO.Response> markAsRented(@PathVariable Long id) {
        return ResponseEntity.ok(accommodationService.markAsRented(id));
    }
}