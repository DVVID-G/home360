package com.pragma.home360.application.services;

import com.pragma.home360.application.dto.request.SaveUserRequest;
import com.pragma.home360.application.dto.response.SaveUserResponse;
import com.pragma.home360.application.dto.response.UserResponse;
import org.springframework.data.domain.Page;

public interface UserService {
    SaveUserResponse saveUser(SaveUserRequest request);
    Page<UserResponse> getUsers(Integer page, Integer size, boolean orderAsc);
}
