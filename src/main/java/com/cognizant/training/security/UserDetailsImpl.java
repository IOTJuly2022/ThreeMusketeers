package com.cognizant.training.security;

import com.cognizant.training.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

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
     * Creates a new UserDetails instance from a User instance.
     *
     * @param user User to create details for
     */
    public UserDetailsImpl(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.authorities = user.getAllPermissions();
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
