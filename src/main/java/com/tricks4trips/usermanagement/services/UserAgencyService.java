package com.tricks4trips.usermanagement.services;

import com.tricks4trips.usermanagement.entities.Credential;
import com.tricks4trips.usermanagement.entities.UserAgency;
import com.tricks4trips.usermanagement.repositories.UserAgencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAgencyService {

    @Autowired
    private UserAgencyRepository userRepository;

    @Autowired
    private CredentialService credentialService;


    public UserAgency createNewUser(UserAgency user) {
        Credential credential = credentialService.createNewCredential(user.getCredential());
        if (credential != null) {
            return userRepository.save(user);
        }
        return null;
    }

    public UserAgency login(String email, String password) {
        Credential credential = credentialService.login(email, password);
        if (credential != null) {
            return userRepository.findByCredential_Id(credential.getId());
        }
        return null;
    }

}