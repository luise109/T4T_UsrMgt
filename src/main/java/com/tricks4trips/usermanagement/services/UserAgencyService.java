package com.tricks4trips.usermanagement.services;

import com.tricks4trips.usermanagement.entities.UserAgency;
import com.tricks4trips.usermanagement.repositories.UserAgencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAgencyService {

    @Autowired
    private UserAgencyRepository userRepository;


    public UserAgency createNewUser(UserAgency user) {
        if (userRepository.findByEmail(user.getEmail()) == null) {
            return userRepository.save(user);
        }
        return null;
    }

    public UserAgency login(String email, String password) {
        if (email != null && password != null) {
            UserAgency userAgency =  userRepository.findByEmailAndPassword(email, password);
            if (userAgency == null) {
                return null;
            }
            userAgency.setPassword("");
            return userAgency;
        }
        return null;
    }

}