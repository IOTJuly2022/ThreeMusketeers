package com.cognizant.training.model;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.*;
@Entity
public class GPU
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Getter
	@Setter
	@JoinColumn(name="ProductID")
	private Product product;
	
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
	private float clockspeed;
	
	@Getter
	@Setter
	private float length;
	
	// ID - int, productid - int, chipset - varchar(255), 
	//memory - int, clockspeed - int, color - varchar(255), length - float
	
	public GPU(Product product, String chipset, float memory, float clockspeed, String color, float length) {
		this.product = product;
		this.chipset = chipset;
		this.memory = memory;
		this.clockspeed = clockspeed;
		this.color = color;
		this.length = length;
	}
}
