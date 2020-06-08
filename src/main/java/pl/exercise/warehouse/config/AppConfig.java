package pl.exercise.warehouse.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.mapstruct.factory.Mappers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.exercise.warehouse.mapper.CategoryMapper;
import pl.exercise.warehouse.mapper.ProducerMapper;
import pl.exercise.warehouse.mapper.ProductMapper;

@Configuration
public class AppConfig {

    @Bean
    public ProductMapper productMapper() {
        return Mappers.getMapper(ProductMapper.class);
    }

    @Bean
    public ProducerMapper producerMapper() {
        return Mappers.getMapper(ProducerMapper.class);
    }

    @Bean
    public CategoryMapper categoryMapper() {
        return Mappers.getMapper(CategoryMapper.class);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().registerModule(
                new JavaTimeModule()
        );
    }

}
