package com.example.lab_1.model.domain;

import com.example.lab_1.model.enums.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

@Getter
@Entity
@Immutable
//@Table(name = "accommodation_details_view", schema = "public")
@Subselect("SELECT a.id AS accommodation_id, a.name AS accommodation_name, a.category, a.num_rooms, " +
        "CONCAT(h.name, ' ', h.surname) AS host_full_name, c.name AS country_name " +
        "FROM accommodation a " +
        "JOIN host h ON a.host_id = h.id " +
        "JOIN country c ON h.country_id = c.id")
@Synchronize({"accommodation", "host", "country"})
public class AccommodationDetailsView {
    @Id
    @Column(name = "accommodation_id")
    private Long accommodationId;

    @Column(name = "accommodation_name")
    private String accommodationName;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "num_rooms")
    private Integer numRooms;

    @Column(name = "host_full_name")
    private String hostFullName;

    @Column(name = "country_name")
    private String countryName;

}