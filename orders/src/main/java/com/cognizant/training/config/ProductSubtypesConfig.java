package com.cognizant.training.config;

import org.springframework.context.annotation.Configuration;

/** Applies necessary configurations Jackson JSON subtypes of Product
 * 
 * 	@author Andrew Kluttz
 */
@Configuration
public class ProductSubtypesConfig {
    /*@Bean
    @Primary
	ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerSubtypes(GPU.class, Memory.class, Motherboard.class, Processor.class);
        return objectMapper;
    }*/
}
