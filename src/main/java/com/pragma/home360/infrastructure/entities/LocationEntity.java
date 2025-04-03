package com.pragma.home360.infrastructure.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String barrio;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private CityEntity city;
    @Transient

    public String getDepartmentName() {
        return city != null ? city.getDeparment().getName() : null;
    }

}
