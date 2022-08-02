package com.cognizant.training.seed;

/**
 * Represents an individual database element that can be seeded.
 *
 * @author William Simpson
 */
public interface IDatabaseSeed {

    /**
     * Seeds the database if it has not been seeded already.
     *
     * @return whether the database was seeded
     */
    boolean run();
}
