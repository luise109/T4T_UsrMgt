package com.tricks4trips.usermanagement.services;

import com.tricks4trips.usermanagement.entities.Credential;
import com.tricks4trips.usermanagement.repositories.CredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CredentialService {

    @Autowired
    private CredentialRepository credentialRepository;

    private boolean isEmailAvailable(String email) {
        return credentialRepository.findByEmail(email) == null;
    }

    public Credential login(String email, String password) {
        return credentialRepository.findByEmailAndPassword(email, password);
    }

    public Credential createNewCredential(Credential credential) {
        if (isEmailAvailable(credential.getEmail())) {
            return credentialRepository.save(credential);
        }
        return null;
    }

}
