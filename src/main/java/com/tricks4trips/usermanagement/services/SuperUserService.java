package com.tricks4trips.usermanagement.services;

import com.tricks4trips.usermanagement.entities.SuperUser;
import com.tricks4trips.usermanagement.repositories.SuperUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuperUserService {

    @Autowired
    private SuperUserRepository userRepository;

    public SuperUser createNewUser(SuperUser user) {
        if (userRepository.findByUsername(user.getUsername()) == null) {
            return userRepository.save(user);
        }
        return null;
    }

    public SuperUser login(String email, String password) {
        if (email != null && password != null) {
            SuperUser superUser = userRepository.findByUsernameAndPassword(email, password);
            superUser.setPassword("");
            return superUser;
        }
        return null;
    }

}