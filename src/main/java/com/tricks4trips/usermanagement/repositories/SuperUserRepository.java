package com.tricks4trips.usermanagement.repositories;


import com.tricks4trips.usermanagement.entities.SuperUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SuperUserRepository extends CrudRepository<SuperUser, Long> {

    SuperUser findByUsername(String username);

    SuperUser findByUsernameAndPassword(String username, String password);

    Optional<SuperUser> findOneByUsername(String username);
}