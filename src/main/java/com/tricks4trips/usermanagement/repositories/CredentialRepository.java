package com.tricks4trips.usermanagement.repositories;

import com.tricks4trips.usermanagement.entities.Credential;
import org.springframework.data.repository.CrudRepository;

public interface CredentialRepository extends CrudRepository<Credential, Long> {
    Credential findByEmail(String email);

    Credential findByEmailAndPassword(String email, String password);
}