package me.ildarorama.module4.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.module.jsr310.Jsr310Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper getModelMapper() {
        var modelMapper = new ModelMapper();
        modelMapper.registerModule(new Jsr310Module());
        return modelMapper;
    }
}