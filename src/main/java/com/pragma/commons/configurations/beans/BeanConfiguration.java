package com.pragma.commons.configurations.beans;

import com.pragma.home360.domain.ports.in.CategoryServicePort;
import com.pragma.home360.domain.ports.in.DeparmentServicePort;
import com.pragma.home360.domain.ports.out.CategoryPersistencePort;
import com.pragma.home360.domain.ports.out.DeparmentPersistencePort;
import com.pragma.home360.domain.ports.out.LocationPersistencePort;
import com.pragma.home360.domain.usecases.CategoryUseCase;
import com.pragma.home360.domain.usecases.DeparmentUsecase;
import com.pragma.home360.infrastructure.adapters.persistence.CategoryPersistenceAdapter;
import com.pragma.home360.infrastructure.adapters.persistence.DeparmentPersistenceAdapter;
import com.pragma.home360.infrastructure.adapters.persistence.LocationPersistenceAdapter;
import com.pragma.home360.infrastructure.mappers.CategoryEntityMapper;
import com.pragma.home360.infrastructure.mappers.DeparmentEntityMapper;
import com.pragma.home360.infrastructure.mappers.LocationEntityMapper;
import com.pragma.home360.infrastructure.repositories.mysql.CategoryRepository;
import com.pragma.home360.infrastructure.repositories.mysql.DeparmentRepository;
import com.pragma.home360.infrastructure.repositories.mysql.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final CategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;
    private final LocationRepository locationRepository;
    private final LocationEntityMapper locationEntityMapper;
    private final DeparmentRepository deparmentRepository;
    private final DeparmentEntityMapper deparmentEntityMapper;

    @Bean
    public CategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }

    @Bean
    public CategoryPersistencePort categoryPersistencePort() {
        return new CategoryPersistenceAdapter(categoryRepository, categoryEntityMapper);
    }
    @Bean
    public LocationPersistencePort locationPersistencePort() {
        return new LocationPersistenceAdapter(locationRepository, locationEntityMapper);
    }
    @Bean
    public DeparmentPersistencePort deparmentPersistencePort() {
        return new DeparmentPersistenceAdapter(deparmentRepository, deparmentEntityMapper);
    }
    @Bean
    public DeparmentServicePort deparmentServicePort() { return new DeparmentUsecase(deparmentPersistencePort()); }


}