package com.cognizant.training.model;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.*;
@Entity
public class Motherboard extends Product{

	@Getter
	@Setter
	@NotNull
	@ManyToOne
	@JoinColumn(name="Socket")
	private Socket socket;
	
	@Getter
	@Setter
	@NotNull
	private String formFactor;
	
	@Getter
	@Setter
	@Min(1)
	private int maxMem;
	
	@Getter
	@Setter
	@Min(1)
	private int memSlots;
	
	@Getter
	@Setter
	@NotNull
	private String color;
	
	public Motherboard(
			String name,
			float price,
			float rating,
 			Socket socket,
			String formFactor,
			int maxMem,
			int memSlots,
			String color
	) {
		super(name, price, rating);
		this.socket = socket;
		this.formFactor = formFactor;
		this.maxMem = maxMem;
		this.memSlots = memSlots;
		this.color = color;
	}

	public Motherboard() { }
}
