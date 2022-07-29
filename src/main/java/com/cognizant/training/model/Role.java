package com.cognizant.training.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    public long id;

    @Getter
    @Setter
    @Column(unique = true, nullable = false)
    public String name;

    @Getter
    @Setter
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "role_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    public Set<Permission> permissions;

    public Role() { }

    @Override
    public String getAuthority() {
        return "ROLE_"+name;
    }
}
