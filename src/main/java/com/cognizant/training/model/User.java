package com.cognizant.training.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents a user of the application. Defines their personal attributes as well as
 * their roles and permissions for the application.
 *
 * @author William Simpson
 */
@Entity
@Table(name = "users")
public class User {

    /**
     * The user's unique identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    /**
     * The email for the user. Used for logging in.
     * <p>
     * This field is required and must be unique across all users.
     */
    @Getter
    @Setter
    @Column(unique = true)
    @NotNull
    private String email;

    /**
     * The password used to authenticate the user. This field is required.
     */
    @Getter
    @Setter
    @NotNull
    private String password;

    /**
     * The user's first name. This field is required.
     */
    @Getter
    @Setter
    @NotNull
    private String firstName;

    /**
     * The user's last name. This field is required.
     */
    @Getter
    @Setter
    @NotNull
    private String lastName;

    /**
     * All the roles granted to the user.
     */
    @Getter
    @Setter
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    /**
     * All direct permissions granted to the user.
     */
    @Getter
    @Setter
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "user_permissions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<Permission> permissions;

    /**
     * Creates a new user
     */
    public User() {
    }

    /**
     * Gets all permissions defined by the roles the user is granted.
     *
     * @return all permissions defined by the roles the user is granted.
     */
    public Set<Permission> getPermissionsFromRoles() {
        return roles.stream()
                .map(Role::getPermissions)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    /**
     * Gets all permissions granted discretely and by roles granted to the user.
     *
     * @return all permissions granted discretely and by roles granted to the user.
     */
    public Set<Permission> getAllPermissions() {
        return Stream.concat(permissions.stream(), getPermissionsFromRoles().stream())
                .collect(Collectors.toSet());
    }
}
