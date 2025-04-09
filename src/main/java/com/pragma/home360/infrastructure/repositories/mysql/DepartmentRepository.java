package com.pragma.home360.infrastructure.repositories.mysql;

import com.pragma.home360.infrastructure.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
    Optional<DepartmentEntity> findByName(String departmentName);
    boolean existsByName(String departmentName);
}
