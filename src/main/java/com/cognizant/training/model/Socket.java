package com.cognizant.training.model;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.*;

@Entity
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
}
