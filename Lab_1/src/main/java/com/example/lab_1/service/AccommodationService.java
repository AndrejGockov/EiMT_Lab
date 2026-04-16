package com.example.lab_1.service;

import com.example.lab_1.events.AccommodationFullyBookedEvent;
import com.example.lab_1.events.AccommodationRentedEvent;
import com.example.lab_1.model.domain.Accommodation;
import com.example.lab_1.model.domain.Host;
import com.example.lab_1.model.dto.AccommodationDTO;
import com.example.lab_1.model.dto.HostDTO;
import com.example.lab_1.model.enums.Category;
import com.example.lab_1.model.exceptions.ResourceNotFoundException;
import com.example.lab_1.repository.AccommodationRepository;
import com.example.lab_1.repository.HostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final HostRepository hostRepository;
    private final HostService hostService;
    private final ApplicationEventPublisher eventPublisher;

    public List<AccommodationDTO.Response> getAllAccommodations() {
        return accommodationRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Lab 2 filtering
    public List<AccommodationDTO.Response> searchAccommodations(int getPage, int pageSize, String sortNameDate, String category, String hostCountry, int numberOfRooms, boolean checkIsRented) {
        // Filtering
        List<AccommodationDTO.Response> accommodations = accommodationRepository.findAll().stream()
                // Filter rented rooms
                .filter(accommodation -> !checkIsRented || !accommodation.getRented())
                // Number of rooms
                .filter(accommodation -> numberOfRooms == -1
                        || accommodation.getNumRooms() == numberOfRooms)
                // Host country
                .filter(accommodation -> hostCountry.equals("/")
                        || accommodation.getHost().getCountry().getName().equalsIgnoreCase(hostCountry))
                // Category
                .filter(accommodation -> category.equals("/")
                        || accommodation.getCategory().name().equalsIgnoreCase(category))
                .map(this::mapToResponse).collect(Collectors.toList());

        // Sorting
        String[] sorts = sortNameDate.split(",");
        boolean nameAsc = sorts[0].equals("asc");
        boolean dateAsc = sorts[0].equals("asc");
        Comparator<AccommodationDTO.Response> comparator = Comparator
                .comparing(AccommodationDTO.Response::getName, nameAsc ? Comparator.naturalOrder() : Comparator.reverseOrder())
                .thenComparing(AccommodationDTO.Response::getCreatedAt, dateAsc ? Comparator.naturalOrder() : Comparator.reverseOrder());

        accommodations.sort(comparator);

        // Manual Pagination
        List<List<AccommodationDTO.Response>> ans = new ArrayList<>();
        List<AccommodationDTO.Response> page = new ArrayList<>();

        for (AccommodationDTO.Response accommodation : accommodations) {
            if (page.size() == pageSize) {
                ans.add(page);
                page = new ArrayList<>();
            }
            page.add(accommodation);
        }

        if (!page.isEmpty() || ans.isEmpty()) {
            ans.add(page);
        }

        accommodations = ans.get(getPage);
        return accommodations;
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

    @Transactional
    public AccommodationDTO.Response markAsRented(Long id) {
        Accommodation accommodation = accommodationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Accommodation not found with id: " + id));

        if (accommodation.getNumRooms() <= 0) {
            throw new IllegalStateException("No available rooms to rent");
        }

        // Decrease number of rooms by 1
        accommodation.setNumRooms(accommodation.getNumRooms() - 1);

        // If no rooms left, mark as fully rented
        if (accommodation.getNumRooms() == 0) {
            accommodation.setRented(true);
        }

        Accommodation updated = accommodationRepository.save(accommodation);

        // Publish rental event
        eventPublisher.publishEvent(new AccommodationRentedEvent(
                this, updated.getId(), updated.getName(), updated.getNumRooms()));

        // Publish fully booked event if applicable
        if (updated.getNumRooms() == 0) {
            eventPublisher.publishEvent(new AccommodationFullyBookedEvent(
                    this, updated.getId(), updated.getName()));
        }

        return mapToResponse(updated);
    }
}