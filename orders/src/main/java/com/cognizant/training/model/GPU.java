package com.cognizant.training.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
public class GPU extends Product
{
	@Getter
	@Setter
	private String chipset;
	
	@Getter
	@Setter
	private String color;
	
	@Getter
	@Setter
	private float memory;
	
	@Getter
	@Setter
	private float clockSpeed;
	
	@Getter
	@Setter
	private float length;
	
	// ID - int, productid - int, chipset - varchar(255), 
	//memory - int, clockspeed - int, color - varchar(255), length - float
	
	public GPU(
			String name,
			float price,
			float rating,
			String chipset,
			float memory,
			float clockSpeed,
			String color,
			float length
	) {
		super(name, price, rating);
		this.chipset = chipset;
		this.memory = memory;
		this.clockSpeed = clockSpeed;
		this.color = color;
		this.length = length;
	}

	public GPU() { }
}
