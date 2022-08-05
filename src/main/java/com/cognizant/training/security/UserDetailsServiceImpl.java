package com.cognizant.training.security;

import com.cognizant.training.model.User;
import com.cognizant.training.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Service handling UserDetailsService implementation. Allowing for custom authentication and authorization.
 *
 * @author William Simpson
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Searches for a user in the database given an email. If it exists, creates the UserDetails for that user.
     * Otherwise, throws an exception.
     *
     * @param email user's email
     * @return UserDetails for the given email
     * @throws UsernameNotFoundException when an email is not found in the database
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        Optional<User> oUser = userRepository.findByEmail(email);

        if (oUser.isEmpty()) throw new UsernameNotFoundException("no user found with given email");

        return oUser.get();
    }
}
