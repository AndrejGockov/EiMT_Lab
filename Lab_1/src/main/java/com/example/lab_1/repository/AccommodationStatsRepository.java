package com.example.lab_1.repository;

import com.example.lab_1.model.domain.AccommodationStats;
import com.example.lab_1.model.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccommodationStatsRepository extends JpaRepository<AccommodationStats, Category> {
//    @Query(value = "SELECT * FROM accommodation_stats_mv", nativeQuery = true)
//    List<AccommodationStats> findAllNative();
}