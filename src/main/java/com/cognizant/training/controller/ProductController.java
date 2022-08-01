package com.cognizant.training.controller;

import java.util.List;
import java.util.function.Function;

import com.cognizant.training.exception.ProductNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.training.model.Product;
import com.cognizant.training.repository.ProductRepository;

@RestController
public class ProductController {

	private final ProductRepository repo;

	ProductController(ProductRepository repos) {
		repo = repos;
	}

	@GetMapping("/v1/products/index")
	public String index() {
		return "Successfully indexed Products table, Controller is running.";
	}

	// GET:: Fetch all Product entities in List form
	@GetMapping("/v1/products")
	List<Product> listAll() {
		return repo.findAll();
	}

	// POST:: Add new Product to database
	@PostMapping("/v1/products")
	Product newProduct(@RequestBody Product newProd) {
		return repo.save(newProd);
	}

	// GET:: Fetch single Product item by its ID
	@GetMapping("/v1/products/{id}")
	Product fetchSingle(@PathVariable Long id) {
		return repo.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
	}

	// PUT:: "Replaces" an entity by re-defining all of its fields except ID
	@PutMapping("/v1/products/{id}")
	Product replaceProduct(@RequestBody Product newProd, @PathVariable Long id) {
		return (repo.findById(id)
				.map(prod -> {
					prod.setName(newProd.getName());
					prod.setPrice(newProd.getPrice());
					prod.setRating(newProd.getRating());
					return repo.save(prod);
				}).orElseGet(() -> {
			return repo.save(newProd);
		}));
	}

	@DeleteMapping("/v1/products/{id}")
	void deleteProduct(@PathVariable Long id) {
		repo.deleteById(id);
	}
}


