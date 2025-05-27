package com.pragma.home360.infrastructure.mappers;

import com.pragma.home360.domain.model.UserModel;
import com.pragma.home360.infrastructure.entities.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {
    UserEntity modelToEntity(UserModel userModel);
    UserModel entityToModel(UserEntity userEntity);
    List<UserModel> entityListToModelList(List<UserEntity> userEntities);
}
