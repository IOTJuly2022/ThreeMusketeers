package com.cognizant.training.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "sockets")
public class Socket {
	//Data type might need to by string: ex. LGA1151, AMD4
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Getter
	@Setter
	@NotNull
	private String name;
	
	public Socket(String name) {
		this.name = name;
	}

	public Socket() { }
}
