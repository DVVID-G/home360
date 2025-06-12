package com.pragma.home360.domain.ports.in;

import com.pragma.home360.domain.model.UserModel;

import java.util.List;

public interface UserServicePort {
    void saveUser(UserModel userModel);
    List<UserModel> getUsers(Integer page, Integer size, boolean orderAsc);
    UserModel getUserByEmail(String email);
    String encryptPassword(String password);
}
