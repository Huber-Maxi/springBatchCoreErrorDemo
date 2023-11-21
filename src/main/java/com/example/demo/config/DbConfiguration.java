package com.example.demo.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.example.demo.config.jpa.CustomJpaRepositoryFactory;
import com.example.demo.config.jpa.CustomJpaRepoFactoryBean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@AutoConfiguration
@Import(DomainConfiguration.class)
@ComponentScan(basePackages = { "com.example.demo" })
public class DbConfiguration {

    @Bean
    public CustomJpaRepoFactoryBean.JpaRepositoryFactoryGenerator jpaRepositoryFactoryGenerator() {
        return CustomJpaRepositoryFactory::new;
    }
}