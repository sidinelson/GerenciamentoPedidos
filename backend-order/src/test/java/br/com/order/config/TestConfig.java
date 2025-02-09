package br.com.order.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @Bean(name = "testModelMapper")  // Nome único para evitar o conflito
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
