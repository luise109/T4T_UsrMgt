package com.tricks4trips.usermanagement.repositories;

import com.tricks4trips.usermanagement.entities.UserAgency;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserAgencyRepository extends CrudRepository<UserAgency, Long> {
    UserAgency findByEmail(String email);
    UserAgency findByEmailAndPassword(String email, String password);

    Optional<UserAgency> findOneByEmail(String username);

}