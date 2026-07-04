package com.example.lab_1.controller;

import com.example.lab_1.model.domain.AccommodationDetailsView;
import com.example.lab_1.model.dto.AccommodationDTO;
import com.example.lab_1.model.projection.AccommodationDetailedProjection;
import com.example.lab_1.model.projection.AccommodationSummaryProjection;
import com.example.lab_1.repository.AccommodationDetailsViewRepository;
import com.example.lab_1.repository.AccommodationRepository;
import com.example.lab_1.service.AccommodationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodations")
@RequiredArgsConstructor
public class AccommodationController {
    private final AccommodationService accommodationService;
    private final AccommodationRepository accommodationRepository;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<AccommodationDTO.Response> getAllAccommodations() {
        return accommodationService.getAllAccommodations();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<AccommodationDTO.Response> getAccommodationById(@PathVariable Long id) {
        return ResponseEntity.ok(accommodationService.getAccommodationById(id));
    }

    @GetMapping("/results")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<AccommodationDTO.Response> searchAccomodation(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "asc,asc") String sortNameDate,
            @RequestParam(required = false, defaultValue = "/") String category,
            @RequestParam(required = false, defaultValue = "/") String hostCountry,
            @RequestParam(required = false, defaultValue = "-1") int numberOfRooms,
            @RequestParam(required = false, defaultValue = "false") boolean hasRooms
    ) {
        return accommodationService.searchAccommodations(page, size, sortNameDate, category, hostCountry, numberOfRooms, hasRooms);
    }

    @GetMapping("/projection")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<AccommodationSummaryProjection>> projectionResponseEntity() {
        List<AccommodationSummaryProjection> projections = accommodationRepository.findAllProjectedBy();
        if (projections.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(projections);
    }

    @GetMapping("/detailed-projection/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<AccommodationDetailedProjection> detailedProjectionResponseEntity(@PathVariable Long id) {
        return accommodationRepository.findProjectedById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Latest 10 accommodations
    @GetMapping("/latest")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<AccommodationDTO.Response> detailedProjectionResponseEntity() {
        return accommodationService.latestAccommodations();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AccommodationDTO.Response> createAccommodation(@Valid @RequestBody AccommodationDTO.Request request) {
        return new ResponseEntity<>(accommodationService.createAccommodation(request), HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AccommodationDTO.Response> updateAccommodation(@PathVariable Long id, @Valid @RequestBody AccommodationDTO.Request request) {
        return ResponseEntity.ok(accommodationService.updateAccommodation(id, request));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteAccommodation(@PathVariable Long id) {
        accommodationService.deleteAccommodation(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/rent/{id}")
    public ResponseEntity<AccommodationDTO.Response> markAsRented(@PathVariable Long id) {
        return ResponseEntity.ok(accommodationService.markAsRented(id));
    }
}