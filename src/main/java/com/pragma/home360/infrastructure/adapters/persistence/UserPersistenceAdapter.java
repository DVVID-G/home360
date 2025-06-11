package com.pragma.home360.infrastructure.adapters.persistence;

import com.pragma.home360.domain.model.UserModel;
import com.pragma.home360.domain.ports.out.UserPersistencePort;
import com.pragma.home360.infrastructure.mappers.UserEntityMapper;
import com.pragma.home360.infrastructure.repositories.mysql.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserPersistencePort {
    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;


    @Override
    public void saveUser(UserModel userModel) {
        userRepository.save(userEntityMapper.modelToEntity(userModel));
    }

    @Override
    public List<UserModel> getUsers(Integer page, Integer size, boolean orderAsc) {
        Pageable pagination;
        if (orderAsc) {
            pagination = PageRequest.of(page, size, Sort.by("id").ascending());
        } else {
            pagination = PageRequest.of(page, size, Sort.by("id").descending());
        }
        return userEntityMapper.entityListToModelList(userRepository.findAll(pagination).getContent());
    }
    @Override
    public void deleteUser(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID del usuario es obligatorio y debe ser mayor que cero");
        }
        userRepository.deleteById(id);
    }
    @Override
    public UserModel getUserByEmail(String email) {
        return userEntityMapper.entityToModel(userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email)));
    }

}