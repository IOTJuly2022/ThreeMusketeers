package com.cognizant.training.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.cognizant.training.model.GPU;
import com.cognizant.training.model.Memory;
import com.cognizant.training.model.Motherboard;
import com.cognizant.training.model.Processor;
import com.fasterxml.jackson.databind.ObjectMapper;

/** Applies necessary configurations Jackson JSON subtypes of Product
 * 
 * 	@author Andrew Kluttz
 */
@Configuration
public class ProductSubtypesConfig {
    @Bean
    @Primary
	ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerSubtypes(GPU.class, Memory.class, Motherboard.class, Processor.class);
        return objectMapper;
    }
}
