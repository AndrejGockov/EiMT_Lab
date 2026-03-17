package com.example.lab_1.controller;

import com.example.lab_1.model.dto.HostDTO;
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

    // GET all hosts
    @GetMapping
    public List<HostDTO.Response> getAllHosts() {
        return hostService.getAllHosts();
    }

    // GET host by ID
    @GetMapping("/{id}")
    public ResponseEntity<HostDTO.Response> getHostById(@PathVariable Long id) {
        return ResponseEntity.ok(hostService.getHostById(id));
    }

    // POST create new host
    @PostMapping
    public ResponseEntity<HostDTO.Response> createHost(@Valid @RequestBody HostDTO.Request request) {
        HostDTO.Response created = hostService.createHost(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // PUT update existing host
    @PutMapping("/{id}")
    public ResponseEntity<HostDTO.Response> updateHost(
            @PathVariable Long id,
            @Valid @RequestBody HostDTO.Request request) {
        HostDTO.Response updated = hostService.updateHost(id, request);
        return ResponseEntity.ok(updated);
    }

    // DELETE host
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHost(@PathVariable Long id) {
        hostService.deleteHost(id);
        return ResponseEntity.noContent().build();
    }
}