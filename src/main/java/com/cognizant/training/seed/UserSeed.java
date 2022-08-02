package com.cognizant.training.seed;

import com.cognizant.training.model.User;
import com.cognizant.training.repository.UserRepository;

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

    public UserSeed(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean run() {
        if (userRepository.findAll().size() > 0) return false;

        User defaultUser = new User();
        defaultUser.setFirstName("William");
        defaultUser.setLastName("Andrews");
        defaultUser.setEmail("user@domain.com");
        defaultUser.setPassword("password");

        this.userRepository.saveAndFlush(defaultUser);

        return true;
    }
}
