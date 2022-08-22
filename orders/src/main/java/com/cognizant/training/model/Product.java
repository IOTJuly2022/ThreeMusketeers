package com.cognizant.training.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Represents a generic product that a customer may add to their order
 * 
 * @author William Simpson
 * @modified Andrew Kluttz, Aug 18 2022
 * 
 * Modification: Implemented Jackson's Json Typing annotations
 * 		Purpose is to allow CatalogController to find and filter product
 * 		subtypes without needing a reference to any other CRUD repo besides
 * 		ProductRepository. Any new product subtypes need to be added to
 * 		the JsonSubTypes annotation
 */
@Entity
@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.PROPERTY,
		property = "type"
)
@JsonSubTypes({
	@JsonSubTypes.Type(value = GPU.class, name = "gpu"),
	@JsonSubTypes.Type(value = Memory.class, name = "memory"),
	@JsonSubTypes.Type(value = Motherboard.class, name = "motherboard"),
	@JsonSubTypes.Type(value = Processor.class, name = "processor"),
	@JsonSubTypes.Type(value = Product.class, name="uncategorized")
})
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "products")
//@MappedSuperclass
public class Product implements Serializable{
	
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
}
