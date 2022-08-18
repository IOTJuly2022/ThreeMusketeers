package com.cognizant.training.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.training.exception.ProductNotFoundException;
import com.cognizant.training.model.Product;
import com.cognizant.training.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.lang.Collections;

/**
 * Accepts requests for any Product entity and its given subtypes. Maps Product
 * objects to their subtypes as defined in #Product annotations using mapper in
 * #ProductSubtypesConfig
 * 
 * Updated Aug 18 2022 to add Catalog services
 *
 * @author Andrew Kluttz
 */

@RestController
public class ProductController {

	private final ProductRepository repo;
	
	@Autowired
	private ObjectMapper typeMapper;

	ProductController(ProductRepository repos) {
		repo = repos;
	}

	@GetMapping("/v1/products/index")
	public String index() {
		return "Successfully indexed Products table, Controller is running.";
	}

	// GET:: Fetch all Product entities in List form. Intended for use in Catalog
	// GUI.
	@GetMapping("/v1/catalog")
	List<Product> listAll() {
		return repo.findAll();
	}

	// GET:: Fetch all Product entities of given type
	// Intended for use when browsing Catalog by specific product subtype, with String stype matching a JsonSubtype in #Product
	@GetMapping("/v1/catalog/{stype}")
	List<? extends Product> listAllByType(@PathVariable String stype) throws JsonProcessingException, JsonMappingException{
		return repo.findAll().stream().filter(prd -> prd.getClass().getSimpleName().equalsIgnoreCase(stype)).toList();
	}

	// POST:: Add new Product to database, mapped to its subtype. Subtype mapped by
	// "type": "typename" in POST body JSON
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
		return (repo.findById(id).map(prod -> {
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