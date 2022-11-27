package com.tricks4trips.usermanagement.repositories;

import com.tricks4trips.usermanagement.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findOneByEmail(String email);

    User findByEmail(String email);
}