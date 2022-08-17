package com.cognizant.training.repository;

import com.cognizant.training.H2JPAConfig;
import com.cognizant.training.ThreeMusketeersApplication;
import com.cognizant.training.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {
                ThreeMusketeersApplication.class,
                H2JPAConfig.class
        }
)
@ActiveProfiles("mem-test")
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

    private final PodamFactory factory = new PodamFactoryImpl();

    @BeforeEach
    public void setup() {
        productRepository.deleteAll();
        productRepository.save(factory.manufacturePojo(Product.class));
        productRepository.flush();
    }

    @Test
    public void canGetAll() {
        int startingCount = productRepository.findAll().size();
        int numToAdd = (new Random()).nextInt(10);
        for (int i = 0; i < numToAdd; i++) {
            productRepository.save(factory.manufacturePojo(Product.class));
        }
        productRepository.flush();

        assertThat(productRepository.findAll().size()).isEqualTo(startingCount + numToAdd);
    }

    @Test
    public void canFindById() {
        Product product = productRepository.saveAndFlush(factory.manufacturePojo(Product.class));

        Optional<Product> foundProduct = productRepository.findById(product.getId());

        assertThat(foundProduct.isPresent()).isTrue();
        assertThat(foundProduct.get().getId()).isEqualTo(product.getId());
    }
}
