package com.example.lab_1.model.domain;

import com.example.lab_1.model.enums.Category;
import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

@Entity
@Immutable
//@Table(name = "accommodation_stats_mv", schema = "public")
@Subselect("SELECT category, " +
        "COUNT(*) AS total_accommodations, " +
        "SUM(num_rooms) AS total_rooms, " +
        "AVG(num_rooms) AS avg_rooms_per_accommodation " +
        "FROM accommodation " +
        "GROUP BY category")
@Synchronize({"accommodation"})
public class AccommodationStats {

    @Id
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "total_accommodations")
    private Long totalAccommodations;

    @Column(name = "total_rooms")
    private Long totalRooms;

    @Column(name = "avg_rooms_per_accommodation")
    private Double avgRoomsPerAccommodation;

    public Category getCategory() {
        return category;
    }

    public Long getTotalAccommodations() {
        return totalAccommodations;
    }

    public Long getTotalRooms() {
        return totalRooms;
    }

    public Double getAvgRoomsPerAccommodation() {
        return avgRoomsPerAccommodation;
    }
}