package com.cognizant.training.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a generic product that a customer may add to their order
 * 
 * @author William Simpson
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "products")
public class Product implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2694617636483893033L;

	/**
	 * The unique ID for the product
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID")
	@Getter
	@Setter
	private long id;
	
	/**
	 * Name of the product
	 */
	@Getter
	@Setter
	@NotNull
	@Column(name = "name", nullable = false)
	private String name;
	
	/**
	 * Price of the product
	 */
	@Getter
	@Setter
	@Min(0)
	@Column(name = "price", nullable = false)
	private float price;
	
	/**
	 * The current 5 star rating of the product. Must be inclusively between 0.0 and 5.0.
	 * Precision to the nearest tenth.
	 */
	@Getter
	@Setter
	@Min(0)
	@Max(5)
	@Column(name = "rating", nullable = false)
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

	@Override
	public boolean equals(Object other) {
		if (other == this) return true;
		if (!(other instanceof Product)) return false;

		return ((Product) other).id == this.id;
	}
}
