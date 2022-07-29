package com.cognizant.training.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @Getter
    @Setter
    @Column(nullable = false, unique = true)
    private String email;

    @Getter
    @Setter
    @Column(nullable = false)
    private String password;

    @Getter
    @Setter
    @Column(nullable = false)
    private String firstName;

    @Getter
    @Setter
    @Column(nullable = false)
    private String lastName;

    public User() {

    }
}
