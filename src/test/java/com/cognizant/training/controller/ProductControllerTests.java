package com.cognizant.training.controller;

import com.cognizant.training.model.Product;
import com.cognizant.training.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTests {

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    private final PodamFactory factory = new PodamFactoryImpl();


    @Test
    @WithMockUser
    public void getById_CanWhenExists() {
        Product product = factory.manufacturePojo(Product.class);

        given(productRepository.findById(product.getId())).willReturn(Optional.of(product));

        ResponseEntity<Product> resp = restTemplate.getForEntity(
                String.format("/v1/products/%d", product.getId()),
                Product.class
        );

        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resp.getBody()).isEqualTo(product);
    }


    @Test
    @WithMockUser
    public void getById_ErrorWhenNotExists() {
        Long id = factory.manufacturePojo(Long.class);
        given(productRepository.findById(id)).willReturn(Optional.empty());

        ResponseEntity<Product> resp = restTemplate.getForEntity(
                String.format("/v1/products/%d", id),
                Product.class
        );

        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
