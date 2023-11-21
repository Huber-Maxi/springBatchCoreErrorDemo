package com.example.demo.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.example.demo.entity.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


@Configuration
@ComponentScan("com.example.demo.entity")
public class DomainConfiguration {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean validator(MessageSource messageSource) {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        //bean.setValidationMessageSource(messageSource);
        return bean;
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
        filterProvider.addFilter(AbstractEntity.NO_ID_FILTER, SimpleBeanPropertyFilter.serializeAllExcept("id", "Id"));
        SimpleModule module = new SimpleModule();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setFilterProvider(filterProvider);
        objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.UPPER_CAMEL_CASE);
        objectMapper.registerModule(module);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        org.modelmapper.config.Configuration configuration = modelMapper.getConfiguration();
        configuration.setFieldMatchingEnabled(true);
        configuration.setFieldAccessLevel(AccessLevel.PRIVATE);
        configuration.setMethodAccessLevel(AccessLevel.PRIVATE);
        configuration.setDeepCopyEnabled(Boolean.TRUE);
        return modelMapper;
    }

}