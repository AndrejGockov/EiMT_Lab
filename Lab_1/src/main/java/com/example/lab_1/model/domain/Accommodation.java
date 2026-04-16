package com.example.lab_1.model.domain;

import com.example.lab_1.model.enums.Category;
import com.example.lab_1.model.enums.Condition;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "accommodation")
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@NamedEntityGraph(name = "Accommodation.withHostAndCountry",
        attributeNodes = {
                @NamedAttributeNode(value = "host", subgraph = "hostCountry")
        },
        subgraphs = {
                @NamedSubgraph(name = "hostCountry", attributeNodes = @NamedAttributeNode("country"))
        })
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Condition condition;

    @Column(nullable = false)
    private Boolean rented = false;

    @Column(name = "num_rooms", nullable = false)
    private Integer numRooms;

    // When accommodation starts with work
    @Column(name = "work_start_date")
    private LocalDateTime workStartDate;

    @ManyToOne
    @JoinColumn(name = "host_id", nullable = false)
    private Host host;

    public Accommodation(String name, Category category, Host host, Condition condition, Integer numRooms, LocalDateTime workStartDate) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.condition = condition;
        this.numRooms = numRooms;
        this.rented = false;
        this.workStartDate = workStartDate;
    }

    public Accommodation(String name, Category category, Host host, Condition condition, Integer numRooms, Boolean rented, LocalDateTime workStartDate) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.condition = condition;
        this.numRooms = numRooms;
        this.rented = rented;
        this.workStartDate = workStartDate;
    }
}