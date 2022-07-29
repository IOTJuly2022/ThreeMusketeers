package com.cognizant.training.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Represents a role a user may have. Can also contain additional discrete permissions given
 * to all users who are granted this role.
 *
 * @author William Simpson
 */
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    /**
     * The role's unique identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private long id;

    /**
     * The role's unique name. This field is required.
     */
    @Getter
    @Setter
    @Column(unique = true)
    @NotNull
    private String name;

    /**
     * All permissions granted to users that have this role
     */
    @Getter
    @Setter
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "role_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<Permission> permissions;

    /**
     * Creates a new role
     */
    public Role() { }

    /**
     * Gets Spring's GrantedAuthority name. Spring boot handles all granted authorities prefixed
     * with `ROLE` to be a role and can be handled as such.
     *
     * @return The role name prefixed with `ROLE_`
     */
    @Override
    public String getAuthority() {
        return "ROLE_"+name;
    }
}
