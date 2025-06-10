package com.pragma.home360.infrastructure.repositories.mysql;

import com.pragma.home360.infrastructure.entities.HouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<HouseEntity, Long> {

}
