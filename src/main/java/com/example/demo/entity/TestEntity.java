package com.example.demo.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
@EqualsAndHashCode(callSuper = false)
@Entity
@RequiredArgsConstructor
@Table(name = "TEST_ENTITY")
public class TestEntity extends AbstractEntity<TestEntity> {

    public TestEntity(String id) {
        this.id = id;
    }

    @Override
    protected String getIdPrefix() {
        return "TEST_ENTITY";
    }

    @Override
    public boolean containsChanges(TestEntity other) {
        return false;
    }

}