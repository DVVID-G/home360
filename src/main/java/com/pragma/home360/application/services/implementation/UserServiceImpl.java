package com.pragma.home360.application.services.implementation;


import com.pragma.home360.application.dto.request.SaveUserRequest;
import com.pragma.home360.application.dto.response.SaveUserResponse;
import com.pragma.home360.application.dto.response.UserResponse;
import com.pragma.home360.application.mappers.UserDtoMapper;
import com.pragma.home360.application.services.UserService;
import com.pragma.home360.domain.ports.in.UserServicePort;
import com.pragma.home360.infrastructure.entities.UserEntity;
import com.pragma.home360.infrastructure.repositories.mysql.UserRepository;
import com.pragma.commons.configurations.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserServicePort userServicePort;
    private final UserDtoMapper userDtoMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SaveUserResponse saveUser(SaveUserRequest request) {
        userServicePort.saveUser(userDtoMapper.requestToModel(request));
        return new SaveUserResponse(Constants.SAVE_USER_RESPONSE_MESSAGE, LocalDateTime.now());
    }

    @Override
    public Page<UserResponse> getUsers(Integer page, Integer size, boolean orderAsc) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<UserEntity> pageEntities = userRepository.findAll(pageable);
        return pageEntities.map(userDtoMapper::entityToResponse);
    }

}
