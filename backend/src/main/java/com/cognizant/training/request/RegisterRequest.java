package com.cognizant.training.request;

import lombok.Getter;
import lombok.Setter;

public class RegisterRequest {
    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;
}
