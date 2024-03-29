package com.cognizant.training.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Motherboard extends Product{

	@Getter
	@Setter
	@NotNull
	private String socket;
	
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
			String socket,
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
		this.color = (Math.random() > 0.5) ? "White" : "Black";
	}

	public Motherboard() { }
}
