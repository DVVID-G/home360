package com.pragma.home360.infrastructure.repositories.mysql;

import com.pragma.home360.infrastructure.entities.LocationEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;


public interface LocationRepository extends JpaRepository<LocationEntity, Long> {
    @Query("SELECT l FROM LocationEntity l JOIN FETCH l.city")
    List<LocationEntity> findAllWithCity(Pageable pageable);
    @Query("""
       SELECT l
       FROM LocationEntity l
       JOIN FETCH l.city c
       JOIN FETCH c.department d
       WHERE c.name LIKE %:query% OR d.name LIKE %:query%
       """)
    List<LocationEntity> searchLocations(@Param("query") String searchText);
    @Query("SELECT l FROM LocationEntity l JOIN FETCH l.city WHERE l.barrio = :locationName")
    Optional<LocationEntity> findByBarrio(@Param("locationName") String locationName);
}
