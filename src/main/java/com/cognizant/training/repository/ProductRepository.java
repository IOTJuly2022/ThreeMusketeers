package com.cognizant.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cognizant.training.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>
{}