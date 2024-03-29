package com.cognizant.training.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
	public Long ownerId;
	
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
	 * Set of all orders
	 */
	@Getter
	@Setter
	@OneToMany
	@JoinColumn(name = "OrderID")
	private Set<OrderDetail> orderDetails = new HashSet<>();


	/**
	 * Creates a new order from the given information
	 * 
	 * @param ownerId user session ID for the order
	 * @param orderStatus custom status of the order
	 */
	public Order(String ownerId, OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Order() { }

	/**
	 * Update the product in/to the set of orderDetails
	 * @param product product
	 * @param count quantity of the product (adding/subtracting)
	 */
	public OrderDetail addProductToOrder(Product product, int count){
		OrderDetail orderDetail = getOrderDetail(product);

		if(orderDetail != null){
			orderDetail.changeQuantity(count);
			return orderDetail;
		}

		orderDetail = new OrderDetail();
		orderDetail.setProduct(product);
		orderDetail.changeQuantity(count);
		orderDetail.setOrder(this);
		orderDetails.add(orderDetail);

		return orderDetail;
	}

	public OrderDetail getOrderDetail(Product product) {
		Optional<OrderDetail> oOrderDetail = orderDetails.stream().filter(orderDetail ->
				orderDetail.getProduct().getId() == product.getId()).findFirst();

		return oOrderDetail.orElse(null);
	}

}
