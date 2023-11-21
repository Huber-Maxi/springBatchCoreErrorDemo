package com.example.demo.config.jpa;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@AutoConfiguration
@EnableJpaRepositories(basePackages = "com.example.demo.repository",
        repositoryFactoryBeanClass = CustomJpaRepoFactoryBean.class)
@EnableTransactionManagement
@EntityScan("com.example.demo.entity")
public class JpaConfig {

    @Bean
    @ConditionalOnMissingBean(CustomJpaRepoFactoryBean.JpaRepositoryFactoryGenerator.class)
    public CustomJpaRepoFactoryBean.JpaRepositoryFactoryGenerator jpaRepositoryFactoryGenerator() {
        return JpaRepositoryFactory::new;
    }
}
