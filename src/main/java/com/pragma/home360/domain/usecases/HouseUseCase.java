package com.pragma.home360.domain.usecases;

import com.pragma.home360.domain.exceptions.CategoryNotFoundException;
import com.pragma.home360.domain.exceptions.InvalidStatusException;
import com.pragma.home360.domain.exceptions.LocationNotFoundException;
import com.pragma.home360.domain.exceptions.MissingRequiredFieldException;
import com.pragma.home360.domain.model.CategoryModel;
import com.pragma.home360.domain.model.HouseModel;
import com.pragma.home360.domain.model.LocationModel;
import com.pragma.home360.domain.model.PublicationStatusModel;
import com.pragma.home360.domain.ports.in.HouseServicePort;
import com.pragma.home360.domain.ports.out.CategoryPersistencePort;
import com.pragma.home360.domain.ports.out.HousePersistencePort;
import com.pragma.home360.domain.ports.out.LocationPersistencePort;

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
