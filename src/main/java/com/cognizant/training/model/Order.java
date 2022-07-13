package com.cognizant.training.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a build that a customer is working on.
 * 
 * @author William Simpson
 */
@Entity
@Table(name = "orders")
public class Order {
	
	/**
	 * The unique ID for the order
	 */
	@Id
	@Getter
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long id;
	
	/**
	 * The user session ID that owns this order
	 */
	@Getter
	@Setter
	@NotNull
	public String owner;
	
	/**
	 * The status of the order
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="OrderStatusID")
	@NotNull
	public OrderStatus orderStatus;
	
	/**
	 * Creates a new order from the given information
	 * 
	 * @param owner user session ID for the order
	 * @param orderStatus custom status of the order
	 */
	public Order(String owner, OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
}
