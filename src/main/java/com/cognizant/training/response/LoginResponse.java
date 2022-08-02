package com.cognizant.training.response;

import lombok.Getter;
import lombok.Setter;

public class LoginResponse {
    @Getter
    @Setter
    private String token;

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String email;
}
