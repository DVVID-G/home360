package com.pragma.home360.infrastructure.repositories.mysql;

import com.pragma.home360.infrastructure.entities.DeparmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeparmentRepository extends JpaRepository<DeparmentEntity, Long> {
    Optional<DeparmentEntity> findByName(String deparmentName);
}
