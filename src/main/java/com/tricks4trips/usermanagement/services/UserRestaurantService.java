package com.tricks4trips.usermanagement.services;

import com.tricks4trips.usermanagement.entities.Credential;
import com.tricks4trips.usermanagement.entities.UserRestaurant;
import com.tricks4trips.usermanagement.repositories.UserRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRestaurantService {

    @Autowired
    private UserRestaurantRepository userRepository;

    @Autowired
    private CredentialService credentialService;


    public UserRestaurant createNewUser(UserRestaurant userRestaurant) {
        Credential credential = credentialService.createNewCredential(userRestaurant.getCredential());
        if (credential != null) {
            return userRepository.save(userRestaurant);
        }
        return null;
    }

    public UserRestaurant login(String email, String password) {
        Credential credential = credentialService.login(email, password);
        if (credential != null) {
            return userRepository.findByCredential_Id(credential.getId());
        }
        return null;
    }

}