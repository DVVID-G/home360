package com.pragma.home360.infrastructure.repositories.mysql;

import com.pragma.home360.infrastructure.entities.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity , Long> {
    Optional<CategoryEntity> findByName(String name);
    Page<CategoryEntity> findAll(Pageable pageable);
}
