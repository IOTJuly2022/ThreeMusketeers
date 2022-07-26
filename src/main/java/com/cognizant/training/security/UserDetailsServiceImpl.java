package com.cognizant.training.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

/**
 * Service handling UserDetailsService implementation. Allowing for custom authentication and authorization.
 *
 * @author William Simpson
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * Currently creates a dummy user for testing.
     * <p>
     * Searches for a user in the database given a username. If it exists, creates the UserDetails for that user.
     * Otherwise, throws an exception.
     *
     * @param username user's username
     * @return UserDetails for the given username
     * @throws UsernameNotFoundException when a username is not found in the database
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return new UserDetailsImpl(1L, "username", "password", "email", new ArrayList<>());
    }
}
