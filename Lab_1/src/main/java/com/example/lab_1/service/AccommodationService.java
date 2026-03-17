package com.example.lab_1.service;

import com.example.lab_1.model.domain.Accommodation;
import com.example.lab_1.model.domain.Host;
import com.example.lab_1.model.dto.AccommodationDTO;
import com.example.lab_1.model.dto.HostDTO;
import com.example.lab_1.model.exceptions.ResourceNotFoundException;
import com.example.lab_1.repository.AccommodationRepository;
import com.example.lab_1.repository.HostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final HostRepository hostRepository;
    private final HostService hostService;

    public List<AccommodationDTO.Response> getAllAccommodations() {
        return accommodationRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public AccommodationDTO.Response getAccommodationById(Long id) {
        Accommodation accommodation = accommodationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Accommodation not found with id: " + id));
        return mapToResponse(accommodation);
    }

    public AccommodationDTO.Response createAccommodation(AccommodationDTO.Request request) {
        Host host = hostRepository.findById(request.getHostId())
                .orElseThrow(() -> new ResourceNotFoundException("Host not found with id: " + request.getHostId()));
        Accommodation accommodation = new Accommodation();
        accommodation.setName(request.getName());
        accommodation.setCategory(request.getCategory());
        accommodation.setCondition(request.getCondition());
        accommodation.setNumRooms(request.getNumRooms());
        accommodation.setHost(host);
        accommodation.setRented(false); // default
        Accommodation saved = accommodationRepository.save(accommodation);
        return mapToResponse(saved);
    }

    public AccommodationDTO.Response updateAccommodation(Long id, AccommodationDTO.Request request) {
        Accommodation accommodation = accommodationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Accommodation not found with id: " + id));
        Host host = hostRepository.findById(request.getHostId())
                .orElseThrow(() -> new ResourceNotFoundException("Host not found with id: " + request.getHostId()));
        accommodation.setName(request.getName());
        accommodation.setCategory(request.getCategory());
        accommodation.setCondition(request.getCondition());
        accommodation.setNumRooms(request.getNumRooms());
        accommodation.setHost(host);
        // rented is not updated via this request; we have separate endpoint
        Accommodation updated = accommodationRepository.save(accommodation);
        return mapToResponse(updated);
    }

    public void deleteAccommodation(Long id) {
        if (!accommodationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Accommodation not found with id: " + id);
        }
        accommodationRepository.deleteById(id);
    }

    public AccommodationDTO.Response markAsRented(Long id) {
        Accommodation accommodation = accommodationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Accommodation not found with id: " + id));
        accommodation.setRented(true);
        // Optionally set condition to BAD? Not required, but you could.
        Accommodation updated = accommodationRepository.save(accommodation);
        return mapToResponse(updated);
    }

    public AccommodationDTO.Response mapToResponse(Accommodation accommodation) {
        AccommodationDTO.Response response = new AccommodationDTO.Response();
        response.setId(accommodation.getId());
        response.setName(accommodation.getName());
        response.setCategory(accommodation.getCategory());
        response.setCondition(accommodation.getCondition());
        response.setRented(accommodation.getRented());
        response.setNumRooms(accommodation.getNumRooms());
        response.setCreatedAt(accommodation.getCreatedAt());
        response.setUpdatedAt(accommodation.getUpdatedAt());
        // Map host
        HostDTO.Response hostResponse = hostService.mapToResponse(accommodation.getHost());
        response.setHost(hostResponse);
        return response;
    }
}