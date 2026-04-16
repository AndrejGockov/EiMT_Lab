package com.example.lab_1.repository;

import com.example.lab_1.model.domain.Accommodation;
import com.example.lab_1.model.projection.AccommodationDetailedProjection;
import com.example.lab_1.model.projection.AccommodationSummaryProjection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
    List<AccommodationSummaryProjection> findAllProjectedBy();

    @EntityGraph("Accommodation.withHostAndCountry")
    @Query("SELECT a.id as id, a.name as name, a.category as category, a.numRooms as numRooms, " + "CONCAT(h.name, ' ', h.surname) as hostFullName, " + "c.name as hostCountryName "
            + "FROM Accommodation a JOIN a.host h JOIN h.country c "
            + "WHERE a.id = :id")
    Optional<AccommodationDetailedProjection> findProjectedById(Long id);
}