package com.cognizant.training.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.*;

/**
 * Represents an single detail for an order. Orders may have many order details.
 * 
 * @author William Simpson
 */
@Entity
@Table(name = "order_details")
public class OrderDetail {
	/**
	 * The unique ID for the order detail
	 */
	@Id
	@Getter
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	/**
	 * The number or products
	 */
	@Getter
	@Setter
	@Min(1)
	private int quantity;
	
	/**
	 * The order that the details belong to
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="OrderID")
	@NotNull
	public Order order;
	
	/**
	 * Need to remove and use inheritance.
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="ProductID")
	@NotNull
	public Product product;

	/**
	 * Creates the details for an order with a quantity and product
	 * 
	 * @param quantity amount of product
	 * @param order order to add details to
	 * @param product product to add to order
	 */
	public OrderDetail(int quantity, Order order, Product product) {
		this.quantity = quantity;
		this.order = order;
		this.product = product;
	}

	public OrderDetail() { }

	/**
	 * Changes the quantity of the product in an order
	 * @param amount amount to change the quantity of the product
	 */
	public void changeQuantity(int amount){
		this.quantity = Math.min(0,this.quantity+amount);
	}
}
