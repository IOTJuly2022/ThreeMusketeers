package com.cognizant.training.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.*;

@Entity
@Table(name = "processors")
public class Processor {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="ProductID")
	private Product product;
	
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
	
	public Processor(Product product, Socket socket, float coreCount, float coreClock, float tdp, boolean integratedGPU) {
		this.product = product;
		this.socket = socket;
		this.coreCount = coreCount;
		this.coreClock = coreClock;
		this.tdp = tdp;
		this.integratedGPU = integratedGPU;
	}
}
