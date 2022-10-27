package com.tricks4trips.usermanagement.services;

import com.tricks4trips.usermanagement.entities.Credential;
import com.tricks4trips.usermanagement.entities.User;
import com.tricks4trips.usermanagement.repositories.CredentialRepository;
import com.tricks4trips.usermanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CredentialService credentialService;


    public User createNewUser(User user) {
        Credential credential = credentialService.createNewCredential(user.getCredential());
        if (credential != null) {
            return userRepository.save(user);
        }
        return null;
    }

}
