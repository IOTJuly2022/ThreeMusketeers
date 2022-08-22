package com.cognizant.training.seed;

import com.cognizant.training.repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    /**
     * Initializes the seeds in-order that they need to be created and seeds them.
     *
     * @param args command line arguments
     */
    @Override
    public void run(String... args) {
        Set<IDatabaseSeed> seeds = new LinkedHashSet<>();
        seeds.add(new OrderStatusSeed(orderStatusRepository));

        for (IDatabaseSeed seed : seeds) {
            seed.run();
        }
    }
}
