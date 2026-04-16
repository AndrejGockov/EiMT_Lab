package com.example.lab_1.controller;

import com.example.lab_1.model.domain.AccommodationDetailsView;
import com.example.lab_1.model.domain.AccommodationStats;
import com.example.lab_1.repository.AccommodationDetailsViewRepository;
import com.example.lab_1.repository.AccommodationStatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accommodations")
public class AccommodationViewController {
    private final AccommodationDetailsViewRepository viewRepository;

    private final AccommodationStatsRepository statsRepository;

    @GetMapping("/views")
    public List<AccommodationDetailsView> getAccommodationDetails() {
        return viewRepository.findAll();
    }

    @GetMapping("/views/stats")
    public List<AccommodationStats> getAccommodationStats() {
//        System.out.println(statsRepository.findAllNative().size());
//        return statsRepository.findAllNative();
        return statsRepository.findAll();
    }
}
