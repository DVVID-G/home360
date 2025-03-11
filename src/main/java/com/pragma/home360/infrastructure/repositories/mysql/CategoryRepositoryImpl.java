/*
package com.pragma.home360.infrastructure.repositories.mysql;

import com.pragma.home360.domain.model.Category;
import com.pragma.home360.domain.ports.out.CategoryRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Category save(Category category) {
        entityManager.persist(category); // Guarda la categoría en la base de datos
        return category;
    }
}

 */