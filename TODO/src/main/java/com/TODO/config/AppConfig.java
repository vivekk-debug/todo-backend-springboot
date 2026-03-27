package com.TODO.config;

import com.TODO.DTO.TodoDto;
import com.TODO.entity.Todo;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);

        modelMapper.typeMap(TodoDto.class, Todo.class)
                .addMappings(mapper -> mapper.skip(Todo::setId));

        return modelMapper;
    }
}