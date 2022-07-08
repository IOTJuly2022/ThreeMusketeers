package com.cognizant.training.model;

import javax.persistence.*;
import javax.validation.constraints.Min;

import lombok.*;
@Entity
public class Processor {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Getter
	@Setter
	@JoinColumn(name="ProductID")
	private Product product;
	
	@Getter
	@Setter
	@JoinColumn(name="Socket")
	private int socket;
	
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
	
	public Processor(Product product, int socket, float coreCount, float coreClock, float tdp, boolean integratedGPU) {
		this.product = product;
		this.socket = socket;
		this.coreCount = coreCount;
		this.coreClock = coreClock;
		this.tdp = tdp;
		this.integratedGPU = integratedGPU;
	}
}
