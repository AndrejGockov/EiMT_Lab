package com.example.lab_1.controller;

import com.example.lab_1.model.dto.AccommodationDTO;
import com.example.lab_1.model.dto.HostDTO;
import com.example.lab_1.model.dto.HostStatsDTO;
import com.example.lab_1.service.AccommodationService;
import com.example.lab_1.service.HostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hosts")
@RequiredArgsConstructor
public class HostController {
    private final HostService hostService;
    private final AccommodationService accommodationService;

    @GetMapping
    public List<HostDTO.Response> getAllHosts() {
        return hostService.getAllHosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HostDTO.Response> getHostById(@PathVariable Long id) {
        return ResponseEntity.ok(hostService.getHostById(id));
    }

    @GetMapping("/{id}/stats")
    public ResponseEntity<HostStatsDTO.Response> getHostStats(@PathVariable Long id) {
        List<AccommodationDTO.Response> hosts = accommodationService.getAllAccommodations().stream()
                .filter(a -> a.getHost().getId().equals(id)).toList();

        return ResponseEntity.ok(hostService.getHostStats(id, hosts));
    }

    @PostMapping("/add")
    public ResponseEntity<HostDTO.Response> createHost(@Valid @RequestBody HostDTO.Request request) {
        HostDTO.Response created = hostService.createHost(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<HostDTO.Response> updateHost(
            @PathVariable Long id,
            @Valid @RequestBody HostDTO.Request request) {
        HostDTO.Response updated = hostService.updateHost(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteHost(@PathVariable Long id) {
        hostService.deleteHost(id);
        return ResponseEntity.noContent().build();
    }
}