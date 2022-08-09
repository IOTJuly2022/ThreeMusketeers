package com.cognizant.training;

import java.security.SecureRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestUtils {
    public static final String LOWER_CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
    public static final String UPPER_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String MIXED_CHARACTERS = LOWER_CHARACTERS + UPPER_CHARACTERS;
    public static final String SPECIAL_CHARACTERS = "@$!%*?&";
    public static final String ALL_CHARACTERS = MIXED_CHARACTERS + SPECIAL_CHARACTERS;

    public static String CreateRandomPassword(int length, boolean validate) {
        String password = IntStream.range(0, length)
                .mapToObj(i -> RandomStringChoice(ALL_CHARACTERS))
                .collect(Collectors.joining());

        if (validate && !password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$")) {
            return CreateRandomPassword(length, true);
        }

        return password;
    }

    public static String CreateRandomEmail() {
        return IntStream.range(0, 15)
                .mapToObj(i -> RandomStringChoice(MIXED_CHARACTERS))
                .collect(Collectors.joining())
                + "@"
                + IntStream.range(0, 8)
                .mapToObj(i -> RandomStringChoice(MIXED_CHARACTERS))
                .collect(Collectors.joining())
                + ".com";
    }

    private static String RandomStringChoice(String available) {
        SecureRandom random = new SecureRandom();
        return String.valueOf(
                available.charAt(
                        random.nextInt(available.length())
                )
        );
    }
}
