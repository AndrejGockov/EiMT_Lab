package com.example.lab_1.controller;

import com.example.lab_1.model.dto.CountryDTO;
import com.example.lab_1.service.CountryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;

    @GetMapping
    public List<CountryDTO.Response> getAllCountries() {
        return countryService.getAllCountries();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryDTO.Response> getCountryById(@PathVariable Long id) {
        return ResponseEntity.ok(countryService.getCountryById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<CountryDTO.Response> createCountry(@Valid @RequestBody CountryDTO.Request request) {
        return new ResponseEntity<>(countryService.createCountry(request), HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<CountryDTO.Response> updateCountry(@PathVariable Long id, @Valid @RequestBody CountryDTO.Request request) {
        return ResponseEntity.ok(countryService.updateCountry(id, request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
        countryService.deleteCountry(id);
        return ResponseEntity.noContent().build();
    }
}
