package com.example.demo.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.entity.AbstractEntity;
import com.example.demo.repository.base.RepositoryService;


@NoRepositoryBean
@RepositoryService
public interface AbstractEntityJpaBaseRepository<T extends AbstractEntity<T>>
        extends  PagingAndSortingRepository<T, String>, CrudRepository<T, String> {

}
