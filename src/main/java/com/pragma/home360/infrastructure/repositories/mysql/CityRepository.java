package com.pragma.home360.infrastructure.repositories.mysql;

import com.pragma.home360.infrastructure.entities.CityEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CityRepository extends JpaRepository<CityEntity, Long> {
    Page<CityEntity> findAll(Pageable pageable);
    /*@Query("SELECT c FROM CityEntity c JOIN FETCH c.deparment WHERE c.name = :name")
    Optional<CityEntity> findByNameWithDepartment(@Param("name") String name);*/
}
