package com.example.demo.repository.jpa.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.EntityInformation;

import com.example.demo.entity.AbstractEntity;
import com.example.demo.listener.AbstractEntityListener;
import com.example.demo.repository.jpa.AbstractEntityJpaBaseRepository;

import jakarta.persistence.EntityManager;

public class AbstractEntityJpaBaseRepositoryImpl<T extends AbstractEntity<T>> extends SimpleJpaRepository<T, String>
        implements AbstractEntityJpaBaseRepository<T> {

    private final EntityInformation<T, ?> entityInformation;
    private final List<AbstractEntityListener<T>> abstractEntityListeners;

    public AbstractEntityJpaBaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation,
            EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.abstractEntityListeners = new ArrayList<>();
        this.entityInformation = entityInformation;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public <S extends T> S save(S entity) {
        boolean isNew = entityInformation.isNew(entity);
        callAbstractEntityListenerBeforeSave(entity, isNew);
        entity.generateId();
        S savedEntity = super.save(entity);
        callAbstractEntityListenerAfterSave(savedEntity, isNew);
        return savedEntity;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public void delete(T entity) {
        callAbstractEntityListenerBeforeDelete(entity);
        super.delete(entity);
        callAbstractEntityListenerAfterDelete(entity);
    }
    private void callAbstractEntityListenerBeforeSave(T entity, boolean isNew) {
        if (isNew) {
            abstractEntityListeners.forEach(listener -> listener.prePersist(entity));
        } else {
            abstractEntityListeners.forEach(listener -> listener.preUpdate(entity));
        }
    }

    private void callAbstractEntityListenerBeforeRestore(T entity) {
        abstractEntityListeners.forEach(listener -> listener.preRestore(entity));
    }

    private void callAbstractEntityListenerBeforeMigrate(T entity) {
        abstractEntityListeners.forEach(listener -> listener.preMigrate(entity));
    }

    private void callAbstractEntityListenerBeforeDelete(T entity) {
        abstractEntityListeners.forEach(listener -> listener.preRemove(entity));
    }

    private void callAbstractEntityListenerAfterSave(T entity, boolean isNew) {
        if (isNew) {
            abstractEntityListeners.forEach(listener -> listener.postPersist(entity));
        } else {
            abstractEntityListeners.forEach(listener -> listener.postUpdate(entity));
        }
    }

    private void callAbstractEntityListenerAfterRestore(T entity) {
        abstractEntityListeners.forEach(listener -> listener.postRestore(entity));
    }

    private void callAbstractEntityListenerAfterMigrate(T entity) {
        abstractEntityListeners.forEach(listener -> listener.postMigrate(entity));
    }

    private void callAbstractEntityListenerAfterDelete(T entity) {
        abstractEntityListeners.forEach(listener -> listener.postRemove(entity));
    }

    final void addAbstractEntityListener(AbstractEntityListener<T> abstractEntityListener) {
        this.abstractEntityListeners.add(abstractEntityListener);
    }
}
