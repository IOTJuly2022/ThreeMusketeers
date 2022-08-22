package com.cognizant.training.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;
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
public class User implements UserDetails {

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
    private Set<Role> roles = new HashSet<>();

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
    private Set<Permission> permissions = new HashSet<>();

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
     * Gets all the authorities granted to the user. Combines authorities granted from roles and authorities granted
     * directly to the user.
     *
     * @return all authorities granted to the user
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Stream.concat(permissions.stream(), getPermissionsFromRoles().stream())
                .collect(Collectors.toSet());
    }

    /**
     * Gets the password hash of the user
     *
     * @return password hash of the user
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Gets the username, also known as the email, of the user
     *
     * @return email of the user
     */
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * Whether the account is expired. Currently, accounts never expire.
     *
     * @return whether the account is non-expired.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Whether the account is locked. Currently, accounts can never lock.
     *
     * @return whether the account is non-locked
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Whether the credentials are expired. Currently, account credentials never expire.
     *
     * @return whether the account credentials are non-expired
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Whether the account is enabled. Currently, accounts are always enabled.
     *
     * @return whether the account is enabled.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
