package com.cognizant.training.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@ExtendWith(SpringExtension.class)
public class ProductControllerTests {

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private MockMvc mockMvc;

    private final PodamFactory factory = new PodamFactoryImpl();

    @Test
    @WithMockUser
    public void getById_CanWhenExists() throws Exception {
        Product product = factory.manufacturePojo(Product.class);

        given(productRepository.findById(product.getId())).willReturn(Optional.of(product));

        mockMvc.perform(get(String.format("/v1/products/%d", product.getId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(product.getId())));
    }


    @Test
    @WithMockUser
    public void getById_ErrorWhenNotExists() throws Exception {
        Long id = factory.manufacturePojo(Long.class);
        given(productRepository.findById(id)).willReturn(Optional.empty());

        mockMvc.perform(get(String.format("/v1/products/%d", id)))
                .andExpect(status().isBadRequest());
    }
}
