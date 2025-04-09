package com.pragma.commons.configurations.beans;

import com.pragma.home360.domain.ports.in.CategoryServicePort;
import com.pragma.home360.domain.ports.in.CityServicePort;
import com.pragma.home360.domain.ports.in.DepartmentServicePort;
import com.pragma.home360.domain.ports.in.LocationServicePort;
import com.pragma.home360.domain.ports.out.CategoryPersistencePort;
import com.pragma.home360.domain.ports.out.CityPersistencePort;
import com.pragma.home360.domain.ports.out.DepartmentPersistencePort;
import com.pragma.home360.domain.ports.out.LocationPersistencePort;
import com.pragma.home360.domain.usecases.CategoryUseCase;
import com.pragma.home360.domain.usecases.CityUseCase;
import com.pragma.home360.domain.usecases.DepartmentUsecase;
import com.pragma.home360.domain.usecases.LocationUseCase;
import com.pragma.home360.infrastructure.adapters.persistence.CategoryPersistenceAdapter;
import com.pragma.home360.infrastructure.adapters.persistence.CityPersistenceAdapter;
import com.pragma.home360.infrastructure.adapters.persistence.DepartmentPersistenceAdapter;
import com.pragma.home360.infrastructure.adapters.persistence.LocationPersistenceAdapter;
import com.pragma.home360.infrastructure.mappers.CategoryEntityMapper;
import com.pragma.home360.infrastructure.mappers.CityEntityMapper;
import com.pragma.home360.infrastructure.mappers.DepartmentEntityMapper;
import com.pragma.home360.infrastructure.mappers.LocationEntityMapper;
import com.pragma.home360.infrastructure.repositories.mysql.CategoryRepository;
import com.pragma.home360.infrastructure.repositories.mysql.CityRepository;
import com.pragma.home360.infrastructure.repositories.mysql.DepartmentRepository;
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
    private final DepartmentRepository departmentRepository;
    private final DepartmentEntityMapper departmentEntityMapper;
    private final CityRepository cityRepository;
    private final CityEntityMapper cityEntityMapper;


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
    public DepartmentPersistencePort deparmentPersistencePort() {
        return new DepartmentPersistenceAdapter(departmentRepository, departmentEntityMapper);
    }
    @Bean
    public DepartmentServicePort deparmentServicePort() { return new DepartmentUsecase(deparmentPersistencePort()); }

    @Bean
    public CityPersistencePort cityPersistencePort() {
        return new CityPersistenceAdapter(cityRepository, cityEntityMapper);
    }
    @Bean
    public CityServicePort cityServicePort() {
        return new CityUseCase(cityPersistencePort(), deparmentPersistencePort());
    }
    @Bean
    public LocationServicePort locationServicePort() {
        return new LocationUseCase(locationPersistencePort(), cityPersistencePort());
    }



}