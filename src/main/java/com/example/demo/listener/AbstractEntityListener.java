package com.example.demo.listener;

import com.example.demo.entity.AbstractEntity;


public interface AbstractEntityListener<T extends AbstractEntity<T>> {

    default void prePersist(T entity) {
    }

    default void preUpdate(T entity) {
    }

    default void preRemove(T entity) {
    }

    default void preRestore(T entity) {
    }

    default void preMigrate(T entity) {
    }

    default void postPersist(T entity) {
    }

    default void postUpdate(T entity) {
    }

    default void postRemove(T entity) {
    }

    default void postRestore(T entity) {
    }

    default void postMigrate(T entity) {
    }
}
