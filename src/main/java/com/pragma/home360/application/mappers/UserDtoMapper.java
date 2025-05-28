package com.pragma.home360.application.mappers;

import com.pragma.home360.application.dto.request.SaveUserRequest;
import com.pragma.home360.application.dto.response.UserResponse;
import com.pragma.home360.domain.model.UserModel;
import com.pragma.home360.infrastructure.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserDtoMapper {
    UserModel requestToModel(SaveUserRequest saveUserRequest);
    UserResponse modelToResponse(UserModel userModel);
    List<UserResponse> modelToResponseList(List<UserModel> userModelList);
    UserResponse entityToResponse(UserEntity userEntity);}
