package com.pragma.home360.domain.ports.out;

import com.pragma.home360.domain.model.UserModel;

import java.util.List;

public interface UserPersistencePort {
    void saveUser(UserModel userModel);
    List<UserModel> getUsers(Integer page, Integer size, boolean orderAsc);
    void deleteUser(Long id);
    UserModel getUserByEmail(String email);
}
