package com.cognizant.training.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Processor extends Product {
	@Getter
	@Setter
	@NotNull
	@ManyToOne
	@JoinColumn(name="Socket")
	private Socket socket;
	
	@Getter
	@Setter
	@Min(1)
	private float coreCount;
	
	@Getter
	@Setter
	@Min(1)
	private float coreClock;
	
	@Getter
	@Setter
	@Min(1)
	private float tdp;
	
	@Getter
	@Setter
	private boolean integratedGPU;
	
	public Processor(
			String name,
			float price,
			float rating,
			Socket socket,
			float coreCount,
			float coreClock,
			float tdp,
			boolean integratedGPU
	) {
		super(name, price, rating);
		this.socket = socket;
		this.coreCount = coreCount;
		this.coreClock = coreClock;
		this.tdp = tdp;
		this.integratedGPU = integratedGPU;
	}

	public Processor() { }
}
