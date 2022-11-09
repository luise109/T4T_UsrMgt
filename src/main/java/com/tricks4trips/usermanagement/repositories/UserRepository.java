package com.tricks4trips.usermanagement.repositories;

import com.tricks4trips.usermanagement.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password);
}