package com.pragma.home360.infrastructure.adapters.persistence;

import com.pragma.commons.configurations.utils.Constants;
import com.pragma.home360.domain.model.HouseModel;
import com.pragma.home360.domain.model.HouseSearchFilters;
import com.pragma.home360.domain.model.PaginatedResult;
import com.pragma.home360.domain.ports.out.HousePersistencePort;
import com.pragma.home360.infrastructure.entities.HouseEntity;
import com.pragma.home360.infrastructure.mappers.HouseEntityMapper;
import com.pragma.home360.infrastructure.repositories.mysql.HouseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class HousePersistenceAdapter implements HousePersistencePort {
    private final HouseRepository houseRepository;
    private final HouseEntityMapper houseEntityMapper;

    @Override
    public void save(HouseModel houseModel) {
        houseRepository.save(houseEntityMapper.modelToEntity(houseModel));
    }

    @Override
    public List<HouseModel> getHouses(Integer page, Integer size, boolean orderAsc) {
        Pageable pagination;
        if (orderAsc) pagination = PageRequest.of(page, size, Sort.by(Constants.PAGEABLE_FIELD_NAME).ascending());
        else pagination = PageRequest.of(page, size, Sort.by(Constants.PAGEABLE_FIELD_NAME).descending());
        return houseEntityMapper.entityListToModelList(houseRepository.findAll(pagination).getContent());
    }
    @Override
    public PaginatedResult<HouseModel> getHousesWithFilters(
            Integer page,
            Integer size,
            HouseSearchFilters filters,
            LocalDateTime currentDate // ✅ Recibe fecha del Domain
    ) {
        // ✅ Solo responsabilidades técnicas
        Sort sort = buildSort(filters.getSortBy(), filters.getSortDirection());
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<HouseEntity> pageEntities = houseRepository.findHousesWithFilters(
                currentDate,
                filters.getLocation(),
                filters.getCategory(),
                filters.getMinRooms(),
                filters.getMaxRooms(),
                filters.getMinBathrooms(),
                filters.getMaxBathrooms(),
                filters.getMinPrice(),
                filters.getMaxPrice(),
                pageable
        );

        // ✅ Convertir Spring Page a modelo de Domain
        List<HouseModel> houses = pageEntities.getContent()
                .stream()
                .map(houseEntityMapper::entityToModel)
                .toList();

        return new PaginatedResult<>(
                houses,
                pageEntities.getNumber(),
                pageEntities.getSize(),
                pageEntities.getTotalElements()
        );
    }

    // ✅ Solo lógica técnica de mapeo de campos
    private Sort buildSort(String sortBy, String sortDirection) {
        if (sortBy == null || sortBy.trim().isEmpty()) {
            return Sort.by("id").ascending();
        }

        String fieldName = mapSortField(sortBy);
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDirection)
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        return Sort.by(direction, fieldName);
    }

    private String mapSortField(String sortBy) {
        return switch (sortBy.toLowerCase()) {
            case "location" -> "location.barrio";
            case "category" -> "category.name";
            case "rooms" -> "numberOfRooms";
            case "bathrooms" -> "numberOfBathrooms";
            case "price" -> "price";
            default -> "id";
        };
    }


}
