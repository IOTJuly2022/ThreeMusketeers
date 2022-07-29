package com.cognizant.training.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "permissions")
public class Permission implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    public long id;

    @Getter
    @Setter
    @Column(unique = true, nullable = false)
    public String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
