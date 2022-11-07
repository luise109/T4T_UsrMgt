package com.tricks4trips.usermanagement.services;

import com.tricks4trips.usermanagement.entities.User;
import com.tricks4trips.usermanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User createNewUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) == null) {
            return userRepository.save(user);
        }
        return null;
    }

    public User login(String email, String password) {
        if (email != null && password != null) {
            User user = userRepository.findByEmailAndPassword(email, password);
            user.setPassword("");
            return user;
        }
        return null;
    }

}
