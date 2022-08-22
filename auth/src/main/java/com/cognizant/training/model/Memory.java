package com.cognizant.training.model;

import javax.persistence.*;
import lombok.*;
@Entity
public class Memory extends Product{
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
	
	public Memory(
			String name,
			float price,
			float rating,
			float speed,
			int packageSize,
			int size,
			String color
	) {
		super(name, price, rating);
		this.speed = speed;
		this.packageSize = packageSize;
		this.size = size;
		this.color = color;
	}


	public Memory() { }
}
