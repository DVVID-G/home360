package com.pragma.home360.domain.usecases;

import com.pragma.home360.domain.exceptions.CategoryNotFoundException;
import com.pragma.home360.domain.exceptions.InvalidStatusException;
import com.pragma.home360.domain.exceptions.LocationNotFoundException;
import com.pragma.home360.domain.exceptions.MissingRequiredFieldException;
import com.pragma.home360.domain.model.*;
import com.pragma.home360.domain.ports.in.HouseServicePort;
import com.pragma.home360.domain.ports.out.CategoryPersistencePort;
import com.pragma.home360.domain.ports.out.HousePersistencePort;
import com.pragma.home360.domain.ports.out.LocationPersistencePort;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


public class HouseUseCase implements HouseServicePort {
    private final HousePersistencePort housePersistencePort;
    private final CategoryPersistencePort categoryPersistencePort;
    private final LocationPersistencePort locationPersistencePort;

    public HouseUseCase(HousePersistencePort housePersistencePort,
                        CategoryPersistencePort categoryPersistencePort,
                        LocationPersistencePort locationPersistencePort) {
        this.housePersistencePort = housePersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
        this.locationPersistencePort = locationPersistencePort;
    }

    @Override
    public void save(HouseModel houseModel, String categoryName, String locationName, String status) {

        validateHouseModel(houseModel);
        validateBusinessRules(categoryName, locationName, status);
        CategoryModel categoryModel = categoryPersistencePort.getCategoryByName(categoryName);
        if (categoryModel == null) {
            throw new CategoryNotFoundException(categoryName);
        }
        houseModel.setCategory(categoryModel);

        LocationModel locationModel = locationPersistencePort.getLocationByName(locationName);
        if (locationModel == null) {
            throw new LocationNotFoundException(locationName);
        }
        houseModel.setLocation(locationModel);

        LocalDateTime now = LocalDateTime.now();
        houseModel.setCreatedAt(now);
        houseModel.setPublishDate(now);

        PublicationStatusModel publicationStatusModel = validateAndParseStatus(status);
        houseModel.setPublicationStatus(publicationStatusModel);

        housePersistencePort.save(houseModel);
    }

    @Override
    public PaginatedResult<HouseModel> getHousesWithFilters(Integer page, Integer size, HouseSearchFilters filters) {
        // ✅ TODAS las validaciones en Domain
        validatePaginationParameters(page, size);
        validateSearchFilters(filters);

        // ✅ Domain genera la fecha actual (lógica de negocio)
        LocalDateTime currentDate = LocalDateTime.now();

        // ✅ Solo delegación a Infrastructure
        return housePersistencePort.getHousesWithFilters(page, size, filters, currentDate);
    }

    // ✅ Validaciones de dominio
    private void validateSearchFilters(HouseSearchFilters filters) {
        if (filters.getSortBy() != null) {
            List<String> validSortFields = List.of("id","location", "category", "rooms", "bathrooms", "price");
            if (!validSortFields.contains(filters.getSortBy().toLowerCase())) {
                throw new IllegalArgumentException("SortBy inválido. Valores permitidos: " + validSortFields);
            }
        }

        if (filters.getSortDirection() != null) {
            List<String> validDirections = List.of("asc", "desc");
            if (!validDirections.contains(filters.getSortDirection().toLowerCase())) {
                throw new IllegalArgumentException("SortDirection inválido. Valores permitidos: " + validDirections);
            }
        }

        validatePriceRange(filters.getMinPrice(), filters.getMaxPrice());
        validateRoomRange(filters.getMinRooms(), filters.getMaxRooms());
        validateBathroomRange(filters.getMinBathrooms(), filters.getMaxBathrooms());
    }
    private void validatePaginationParameters(Integer page, Integer size) {
        if (page < 0) {
            throw new IllegalArgumentException("Page debe ser mayor o igual a 0");
        }
        if (size <= 0 || size > 100) {
            throw new IllegalArgumentException("Size debe estar entre 1 y 100");
        }
    }
    private void validatePriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        if (minPrice != null && minPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Precio mínimo debe ser mayor o igual a 0");
        }
        if (maxPrice != null && maxPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Precio máximo debe ser mayor o igual a 0");
        }
        if (minPrice != null && maxPrice != null && minPrice.compareTo(maxPrice) > 0) {
            throw new IllegalArgumentException("Precio mínimo no puede ser mayor al precio máximo");
        }
    }

    private void validateRoomRange(Integer minRooms, Integer maxRooms) {
        if (minRooms != null && minRooms < 0) {
            throw new IllegalArgumentException("Número mínimo de cuartos debe ser mayor o igual a 0");
        }
        if (maxRooms != null && maxRooms < 0) {
            throw new IllegalArgumentException("Número máximo de cuartos debe ser mayor o igual a 0");
        }
        if (minRooms != null && maxRooms != null && minRooms > maxRooms) {
            throw new IllegalArgumentException("Número mínimo de cuartos no puede ser mayor al máximo");
        }
    }

    private void validateBathroomRange(Integer minBathrooms, Integer maxBathrooms) {
        if (minBathrooms != null && minBathrooms < 0) {
            throw new IllegalArgumentException("Número mínimo de baños debe ser mayor o igual a 0");
        }
        if (maxBathrooms != null && maxBathrooms < 0) {
            throw new IllegalArgumentException("Número máximo de baños debe ser mayor o igual a 0");
        }
        if (minBathrooms != null && maxBathrooms != null && minBathrooms > maxBathrooms) {
            throw new IllegalArgumentException("Número mínimo de baños no puede ser mayor al máximo");
        }
    }


    private void validateHouseModel(HouseModel houseModel) {
        if (houseModel.getName() == null || houseModel.getName().trim().isEmpty()) {
            throw new MissingRequiredFieldException("name");
        }
        if (houseModel.getDescription() == null || houseModel.getDescription().trim().isEmpty()) {
            throw new MissingRequiredFieldException("description");
        }
        if (houseModel.getNumberOfRooms() == null || houseModel.getNumberOfRooms() <= 0) {
            throw new MissingRequiredFieldException("numberOfRooms");
        }
        if (houseModel.getNumberOfBathrooms() == null || houseModel.getNumberOfBathrooms() <= 0) {
            throw new MissingRequiredFieldException("numberOfBathrooms");
        }
        if (houseModel.getPrice() == null || houseModel.getPrice() <= 0) {
            throw new MissingRequiredFieldException("price");
        }
    }
    private void validateBusinessRules(String categoryName, String locationName, String status) {
        if (categoryName == null || categoryName.trim().isEmpty()) {
            throw new MissingRequiredFieldException("categoryName");
        }
        if (locationName == null || locationName.trim().isEmpty()) {
            throw new MissingRequiredFieldException("locationName");
        }
        if (status == null || status.trim().isEmpty()) {
            throw new MissingRequiredFieldException("status");
        }
    }
    private PublicationStatusModel validateAndParseStatus(String status) {
        try {
            return PublicationStatusModel.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidStatusException(status);
        }
    }



    @Override
    public List<HouseModel> getHouses(Integer page, Integer size, boolean orderAsc) {
        return housePersistencePort.getHouses(page, size, orderAsc);
    }
}
