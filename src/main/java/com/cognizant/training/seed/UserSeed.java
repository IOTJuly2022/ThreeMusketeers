package com.cognizant.training.seed;

import com.cognizant.training.model.User;
import com.cognizant.training.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Seeds the User table in the database if no users exists.
 *
 * @author William Simpson
 */
public class UserSeed implements IDatabaseSeed {

    /**
     * User repository used to save seeded users to the database.
     */
    private UserRepository userRepository;

    /**
     * The password encoder to use when encoding saving to the database.
     */
    private PasswordEncoder passwordEncoder;

    public UserSeed(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean run() {
        if (userRepository.findAll().size() > 0) return false;

        User defaultUser = new User();
        defaultUser.setFirstName("William");
        defaultUser.setLastName("Andrews");
        defaultUser.setEmail("user@domain.com");
        defaultUser.setPassword(passwordEncoder.encode("password"));

        this.userRepository.saveAndFlush(defaultUser);

        return true;
    }
}
