package com.example.lab_1.repository;

import com.example.lab_1.model.domain.AccommodationDetailsView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationDetailsViewRepository extends JpaRepository<AccommodationDetailsView, Long> {
    
}