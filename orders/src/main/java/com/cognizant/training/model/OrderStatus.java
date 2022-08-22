package com.cognizant.training.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Represents the status for an Order.
 * 
 * @author William Simpson
 */
@Entity
@Table(name = "order_statuses")
public class OrderStatus {
	
	/**
	 * The unique ID for the order status
	 */
	@Id
	@Getter
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long id;
	
	/**
	 * The name of the order status
	 */
	@Getter
	@Setter
	@NotNull
	public String name;
	
	/**
	 * Creates a new order status with a given name
	 * 
	 * @param name name of the order status
	 */
	public OrderStatus(String name) {
		this.name = name;
	}

	public OrderStatus() { }
}
