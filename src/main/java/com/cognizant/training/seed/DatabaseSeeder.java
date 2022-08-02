package com.cognizant.training.seed;

import com.cognizant.training.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    /**
     * Initializes the seeds in-order that they need to be created and seeds them.
     *
     * @param args command line arguments
     */
    @Override
    public void run(String... args) {
        Set<IDatabaseSeed> seeds = new LinkedHashSet<>();
        seeds.add(new UserSeed(userRepository));

        for (IDatabaseSeed seed : seeds) {
            seed.run();
        }
    }
}
