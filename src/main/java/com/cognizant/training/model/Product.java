package com.cognizant.training.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Getter
	@Setter
	@NotNull
	private String name;
	
	@Getter
	@Setter
	@Min(0)
	private Double price;
	
	@Getter
	@Setter
	@Min(0)
	@Max(5)
	private Double rating;
	
	public Product(String name, Double price, Double rating) {
		this.name = name;
		this.price = price;
		this.rating = rating;
	}
}
