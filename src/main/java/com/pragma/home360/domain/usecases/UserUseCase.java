package com.pragma.home360.domain.usecases;

import com.pragma.home360.domain.model.UserModel;
import com.pragma.home360.domain.ports.in.UserServicePort;
import com.pragma.home360.domain.ports.out.UserPersistencePort;
import com.pragma.home360.infrastructure.adapters.security.PasswordEncoderAdapter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserUseCase implements UserServicePort {
    private final UserPersistencePort userPersistencePort;
    private final PasswordEncoderAdapter passwordEncoderAdapter;


    @Override
    public void saveUser(UserModel userModel) {
        userModel.setPassword(encryptPassword(userModel.getPassword()));
        userPersistencePort.saveUser(userModel);
    }
    @Override
    public String encryptPassword(String password) {
        return passwordEncoderAdapter.encode(password);
    }
    @Override
    public List<UserModel> getUsers(Integer page, Integer size, boolean orderAsc) {
        return userPersistencePort.getUsers(page, size, orderAsc);
    }
    @Override
    public UserModel getUserByEmail(String email) {
        return userPersistencePort.getUserByEmail(email);
    }

}
