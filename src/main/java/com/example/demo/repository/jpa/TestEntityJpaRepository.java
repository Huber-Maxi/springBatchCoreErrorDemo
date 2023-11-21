package com.example.demo.repository.jpa;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.lang.NonNull;

import com.example.demo.entity.TestEntity;

public interface TestEntityJpaRepository extends AbstractEntityJpaBaseRepository<TestEntity>,
        TestEntityJpaRepositoryCustom {



    @Override
    @EntityGraph(value = "TestEntity.allItems", type = EntityGraph.EntityGraphType.LOAD)
    @NonNull
    Page<TestEntity> findAll(@NonNull Pageable pageable);

    @EntityGraph(value = "TestEntity.allItems", type = EntityGraph.EntityGraphType.LOAD)
    List<TestEntity> findAll(Specification<TestEntity> spec);

    @SuppressWarnings("NullableProblems")
    @EntityGraph(value = "TestEntity.allItems", type = EntityGraph.EntityGraphType.LOAD)
    Optional<TestEntity> findById(String id);

    @EntityGraph(value = "TestEntity.allItems", type = EntityGraph.EntityGraphType.LOAD)
    Set<TestEntity> findAllByTagCodeIn(Set<String> tagCodes);

}