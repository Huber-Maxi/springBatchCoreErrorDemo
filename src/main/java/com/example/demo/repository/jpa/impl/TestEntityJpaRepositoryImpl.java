package com.example.demo.repository.jpa.impl;


import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.repository.jpa.TestEntityJpaRepository;
import com.example.demo.repository.jpa.TestEntityJpaRepositoryCustom;


public class TestEntityJpaRepositoryImpl implements TestEntityJpaRepositoryCustom {

    @Autowired
    public TestEntityJpaRepository repository;

}

