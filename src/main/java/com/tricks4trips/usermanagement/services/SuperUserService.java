package com.tricks4trips.usermanagement.services;

import com.tricks4trips.usermanagement.entities.Credential;
import com.tricks4trips.usermanagement.entities.SuperUser;
import com.tricks4trips.usermanagement.repositories.SuperUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuperUserService {

    @Autowired
    private SuperUserRepository userRepository;

    @Autowired
    private CredentialService credentialService;


    public SuperUser createNewUser(SuperUser user) {
        Credential credential = credentialService.createNewCredential(user.getCredential());
        if (credential != null) {
            return userRepository.save(user);
        }
        return null;
    }

    public SuperUser login(String email, String password) {
        Credential credential = credentialService.login(email, password);
        if (credential != null) {
            return userRepository.findByCredential_Id(credential.getId());
        }
        return null;
    }

}