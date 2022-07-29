package com.cognizant.training.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Represents an individual permission a user may be granted.
 *
 * @author William Simpson
 */
@Entity
@Table(name = "permissions")
public class Permission implements GrantedAuthority {

    /**
     * The permission's unique identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    public long id;

    /**
     * The permissions unique name. This field is required.
     */
    @Getter
    @Setter
    @Column(unique = true, nullable = false)
    public String name;

    /**
     * Gets Spring's GrantedAuthority name.
     *
     * @return the name of the permission.
     */
    @Override
    public String getAuthority() {
        return name;
    }
}
