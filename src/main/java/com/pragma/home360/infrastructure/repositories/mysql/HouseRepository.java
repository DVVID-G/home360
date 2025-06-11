package com.pragma.home360.infrastructure.repositories.mysql;

import com.pragma.home360.infrastructure.entities.HouseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface HouseRepository extends JpaRepository<HouseEntity, Long> {

    @Query("""
        SELECT h FROM HouseEntity h 
        WHERE h.publishDate <= :currentDate 
        AND h.status = 'PUBLICADA'
        AND (:location IS NULL OR LOWER(h.location.barrio) LIKE LOWER(CONCAT('%', :location, '%')))
        AND (:category IS NULL OR LOWER(h.category.name) LIKE LOWER(CONCAT('%', :category, '%')))
        AND (:minRooms IS NULL OR h.numberOfRooms >= :minRooms)
        AND (:maxRooms IS NULL OR h.numberOfRooms <= :maxRooms)
        AND (:minBathrooms IS NULL OR h.numberOfBathrooms >= :minBathrooms)
        AND (:maxBathrooms IS NULL OR h.numberOfBathrooms <= :maxBathrooms)
        AND (:minPrice IS NULL OR h.price >= :minPrice)
        AND (:maxPrice IS NULL OR h.price <= :maxPrice)
        """)
    Page<HouseEntity> findHousesWithFilters(
            @Param("currentDate") LocalDateTime currentDate,
            @Param("location") String location,
            @Param("category") String category,
            @Param("minRooms") Integer minRooms,
            @Param("maxRooms") Integer maxRooms,
            @Param("minBathrooms") Integer minBathrooms,
            @Param("maxBathrooms") Integer maxBathrooms,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice,
            Pageable pageable
    );


}
