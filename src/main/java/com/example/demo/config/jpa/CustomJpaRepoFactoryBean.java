package com.example.demo.config.jpa;

import java.io.Serializable;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import jakarta.persistence.EntityManager;

public class CustomJpaRepoFactoryBean<R extends SimpleJpaRepository<T, I>, T, I extends Serializable>
        extends JpaRepositoryFactoryBean<R, T, I> {

    private final JpaRepositoryFactoryGenerator jpaRepositoryFactoryGenerator;

    public CustomJpaRepoFactoryBean(Class<? extends R> repositoryInterface,
            JpaRepositoryFactoryGenerator jpaRepositoryFactoryGenerator) {
        super(repositoryInterface);
        this.jpaRepositoryFactoryGenerator = jpaRepositoryFactoryGenerator;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return jpaRepositoryFactoryGenerator.createJpaRepositoryFactory(entityManager);
    }

    public interface JpaRepositoryFactoryGenerator {
        RepositoryFactorySupport createJpaRepositoryFactory(EntityManager entityManager);
    }
}