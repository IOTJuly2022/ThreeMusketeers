package com.cognizant.training.model;

import javax.persistence.*;
import lombok.*;
@Entity
public class Memory{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "ProductID")
	private Product product;
	
	@Getter
	@Setter
	private float speed;
	
	@Getter
	@Setter
	private int packageSize;
	
	@Getter
	@Setter
	private int size;
	
	@Getter
	@Setter
	private String color;
	
	public Memory(Product product,float speed, int packageSize, int size, String color) {
		this.product = product;
		this.speed = speed;
		this.packageSize = packageSize;
		this.size = size;
		this.color = color;
	}
	
	
}
