package com.tricks4trips.usermanagement.services;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class EncryptPassword {

    public String encrypt(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean compare(String password, String encryptedPassword) {
        return BCrypt.checkpw(password, encryptedPassword);
    }

}
