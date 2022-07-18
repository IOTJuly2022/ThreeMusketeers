package com.cognizant.training.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a generic product that a customer may add to their order
 * 
 * @author William Simpson
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "products")
public abstract class Product {
	
	/**
	 * The unique ID for the product
	 */
	@Id
	@Getter
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	/**
	 * Name of the product
	 */
	@Getter
	@Setter
	@NotNull
	private String name;
	
	/**
	 * Price of the product
	 */
	@Getter
	@Setter
	@Min(0)
	private float price;
	
	/**
	 * The current 5 star rating of the product. Must be inclusively between 0.0 and 5.0.
	 * Precision to the nearest tenth.
	 */
	@Getter
	@Setter
	@Min(0)
	@Max(5)
	private float rating;
	
	/**
	 * Creates a new product given a name, price and rating
	 * 
	 * @param name name of the product
	 * @param price how much the product costs
	 * @param rating current user 5 star rating
	 */
	public Product(String name, float price, float rating) {
		this.name = name;
		this.price = price;
		this.rating = rating;
	}

	public Product() { }
}
