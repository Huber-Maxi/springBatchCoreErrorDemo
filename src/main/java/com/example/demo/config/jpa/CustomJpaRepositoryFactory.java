package com.example.demo.config.jpa;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.RepositoryMetadata;

import com.example.demo.entity.AbstractEntity;
import com.example.demo.entity.TestEntity;
import com.example.demo.repository.jpa.impl.AbstractEntityJpaBaseRepositoryImpl;
import com.example.demo.repository.jpa.impl.TestEntityJpaBaseRepositoryImpl;

import jakarta.persistence.EntityManager;


public class CustomJpaRepositoryFactory extends JpaRepositoryFactory {

    Map<Class<? extends AbstractEntity<?>>, Class<?
            extends AbstractEntityJpaBaseRepositoryImpl<?>>> repositoryBaseClasses;

    public CustomJpaRepositoryFactory(EntityManager entityManager) {
        super(entityManager);
        this.repositoryBaseClasses = new HashMap<>();
        repositoryBaseClasses.put(TestEntity.class, TestEntityJpaBaseRepositoryImpl.class);

    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
        Optional<Class<?>> baseClass = Optional.ofNullable(repositoryBaseClasses.get(metadata.getDomainType()));
        return baseClass.orElse(AbstractEntityJpaBaseRepositoryImpl.class);
    }
}