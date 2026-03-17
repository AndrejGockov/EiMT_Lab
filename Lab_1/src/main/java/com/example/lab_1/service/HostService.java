package com.example.lab_1.service;

import com.example.lab_1.model.domain.Country;
import com.example.lab_1.model.domain.Host;
import com.example.lab_1.model.dto.CountryDTO;
import com.example.lab_1.model.dto.HostDTO;
import com.example.lab_1.model.exceptions.ResourceNotFoundException;
import com.example.lab_1.repository.CountryRepository;
import com.example.lab_1.repository.HostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HostService {
    private final HostRepository hostRepository;
    private final CountryRepository countryRepository;
    private final CountryService countryService; // to reuse mapping

    public List<HostDTO.Response> getAllHosts() {
        return hostRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public HostDTO.Response getHostById(Long id) {
        Host host = hostRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Host not found with id: " + id));
        return mapToResponse(host);
    }

    public HostDTO.Response createHost(HostDTO.Request request) {
        Country country = countryRepository.findById(request.getCountryId())
                .orElseThrow(() -> new ResourceNotFoundException("Country not found with id: " + request.getCountryId()));
        Host host = new Host();
        host.setName(request.getName());
        host.setSurname(request.getSurname());
        host.setCountry(country);
        Host saved = hostRepository.save(host);
        return mapToResponse(saved);
    }

    public HostDTO.Response updateHost(Long id, HostDTO.Request request) {
        Host host = hostRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Host not found with id: " + id));
        Country country = countryRepository.findById(request.getCountryId())
                .orElseThrow(() -> new ResourceNotFoundException("Country not found with id: " + request.getCountryId()));
        host.setName(request.getName());
        host.setSurname(request.getSurname());
        host.setCountry(country);
        Host updated = hostRepository.save(host);
        return mapToResponse(updated);
    }

    public void deleteHost(Long id) {
        if (!hostRepository.existsById(id)) {
            throw new ResourceNotFoundException("Host not found with id: " + id);
        }
        hostRepository.deleteById(id);
    }

    public HostDTO.Response mapToResponse(Host host) {
        HostDTO.Response response = new HostDTO.Response();
        response.setId(host.getId());
        response.setName(host.getName());
        response.setSurname(host.getSurname());
        response.setCreatedAt(host.getCreatedAt());
        response.setUpdatedAt(host.getUpdatedAt());
        // Map country
        CountryDTO.Response countryResponse = countryService.mapToResponse(host.getCountry());
        response.setCountry(countryResponse);
        return response;
    }
}
