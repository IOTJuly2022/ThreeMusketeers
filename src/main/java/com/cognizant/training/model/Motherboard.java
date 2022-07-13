package com.cognizant.training.model;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.*;
@Entity
@Table(name = "motherboards")
public class Motherboard {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="ProductID")
	private Product product;
	
	//Does this need a @JoinColumn
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
	
	public Motherboard(Product product, Socket socket, String formFactor, int maxMem, int memSlots, String color) {
		this.product = product;
		this.socket = socket;
		this.formFactor = formFactor;
		this.maxMem = maxMem;
		this.memSlots = memSlots;
		this.color = color;
	}
}
