package com.example.lab_1.service;


import com.example.lab_1.model.domain.Country;
import com.example.lab_1.model.dto.CountryDTO;
import com.example.lab_1.model.exceptions.ResourceNotFoundException;
import com.example.lab_1.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;

    public List<CountryDTO.Response> getAllCountries() {
        return countryRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public CountryDTO.Response getCountryById(Long id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country not found with id: " + id));
        return mapToResponse(country);
    }

    public CountryDTO.Response createCountry(CountryDTO.Request request) {
        Country country = new Country();
        country.setName(request.getName());
        country.setContinent(request.getContinent());
        Country saved = countryRepository.save(country);
        return mapToResponse(saved);
    }

    public CountryDTO.Response updateCountry(Long id, CountryDTO.Request request) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country not found with id: " + id));
        country.setName(request.getName());
        country.setContinent(request.getContinent());
        Country updated = countryRepository.save(country);
        return mapToResponse(updated);
    }

    public void deleteCountry(Long id) {
        if (!countryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Country not found with id: " + id);
        }
        countryRepository.deleteById(id);
    }

    public CountryDTO.Response mapToResponse(Country country) {
        CountryDTO.Response response = new CountryDTO.Response();
        response.setId(country.getId());
        response.setName(country.getName());
        response.setContinent(country.getContinent());
        return response;
    }
}