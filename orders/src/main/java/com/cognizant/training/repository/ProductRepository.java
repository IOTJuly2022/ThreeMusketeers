package com.cognizant.training.repository;

import com.cognizant.training.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>
{}