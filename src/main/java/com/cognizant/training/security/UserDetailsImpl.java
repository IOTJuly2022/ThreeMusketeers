package com.cognizant.training.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Implements the UserDetails for authenticated users of the application.
 *
 * @author William Simpson
 */
public class UserDetailsImpl implements UserDetails {

    /**
     * The users unique ID
     */
    @Getter
    @Setter
    private long id;

    /**
     * Username for the user
     */
    private final String username;

    /**
     * Password for the user
     */
    private final String password;

    /**
     * Authorities granted to the user
     */
    private final Collection<? extends GrantedAuthority> authorities;

    /**
     * Email of the user
     */
    @Getter
    @Setter
    private String email;

    /**
     * Creates a new user UserDetailsImpl given an id, user, password, email, and authorities
     *
     * @param id       user's unique id
     * @param username user's username
     * @param password user's password
     * @param email    user's email
     */
    public UserDetailsImpl(
            long id,
            String username,
            String password,
            String email,
            Collection<? extends GrantedAuthority> authorities
    ) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
    }

    /**
     * Gets the authorities granted to the user
     *
     * @return all authorities granted to the user
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
     * Gets the username of the user
     *
     * @return username of the user
     */
    @Override
    public String getUsername() {
        return username;
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
