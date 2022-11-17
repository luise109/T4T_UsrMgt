package com.tricks4trips.usermanagement.security;

import lombok.Data;

@Data
public class AuthCredentials {
    private String email;
    private String password;

}
