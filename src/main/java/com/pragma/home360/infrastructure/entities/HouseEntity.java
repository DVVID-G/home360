package com.pragma.home360.infrastructure.entities;

import com.pragma.home360.domain.model.PublicationStatusModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "houses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    private Integer numberOfRooms;
    private Integer numberOfBathrooms;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private LocationEntity location;

    private LocalDateTime publishDate;

    @Enumerated(EnumType.STRING)
    private PublicationStatusModel status;
    private LocalDateTime createdAt;
}
