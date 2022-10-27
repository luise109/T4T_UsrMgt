package com.tricks4trips.usermanagement.repositories;


import com.tricks4trips.usermanagement.entities.SuperUser;
import org.springframework.data.repository.CrudRepository;

public interface SuperUserRepository extends CrudRepository<SuperUser, Long> {
    SuperUser findByCredential_Id(Long id);
}