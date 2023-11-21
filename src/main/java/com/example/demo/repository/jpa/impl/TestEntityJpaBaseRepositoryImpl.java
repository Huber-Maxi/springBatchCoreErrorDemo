package com.example.demo.repository.jpa.impl;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;

import com.example.demo.entity.TestEntity;


import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestEntityJpaBaseRepositoryImpl extends AbstractEntityJpaBaseRepositoryImpl<TestEntity> {

    public TestEntityJpaBaseRepositoryImpl(
            JpaEntityInformation<TestEntity, String> entityInformation,
            EntityManager entityManager) {
        super(entityInformation, entityManager);
    }
}
