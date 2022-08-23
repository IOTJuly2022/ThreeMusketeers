package com.cognizant.training.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
public class Memory extends Product{
	@Getter
	@Setter
	private String speed;
	
	@Getter
	@Setter
	private String packageSize;
	
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
			String speed,
			String packageSize,
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
