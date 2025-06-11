package com.pragma.commons.configurations.beans;

import com.pragma.home360.domain.usecases.CategoryUseCase;
import com.pragma.home360.domain.ports.in.*;
import com.pragma.home360.domain.ports.out.*;
import com.pragma.home360.domain.usecases.AuthenticationUseCase;
import com.pragma.home360.domain.usecases.CityUseCase;
import com.pragma.home360.domain.usecases.DepartmentUsecase;
import com.pragma.home360.domain.usecases.HouseUseCase;
import com.pragma.home360.domain.usecases.LocationUseCase;
import com.pragma.home360.domain.usecases.UserUseCase;
import com.pragma.home360.infrastructure.adapters.persistence.*;
import com.pragma.home360.infrastructure.adapters.security.PasswordEncoderAdapter;
import com.pragma.home360.infrastructure.mappers.*;
import com.pragma.home360.infrastructure.repositories.mysql.*;
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
    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    private final PasswordEncoderAdapter passwordEncoderAdapter;
    private final HouseRepository houseRepository;
    private final HouseEntityMapper houseEntityMapper;



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
    @Bean
    public UserPersistencePort userPersistencePort() {
        return new UserPersistenceAdapter(userRepository, userEntityMapper);
    }
    @Bean
    public UserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort(), passwordEncoderAdapter);
    }

    @Bean
    public AuthenticationServicePort authenticationServicePort(AuthenticationPersistencePort authenticationPersistencePort) {
        return new AuthenticationUseCase(authenticationPersistencePort);
    }
    @Bean
    public HouseServicePort houseServicePort() {
        return new HouseUseCase(housePersistencePort(), categoryPersistencePort(), locationPersistencePort());
    }

    @Bean
    public HousePersistencePort housePersistencePort() {
        return new HousePersistenceAdapter(houseRepository, houseEntityMapper);
    }


}