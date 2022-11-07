package com.tricks4trips.usermanagement.repositories;


import com.tricks4trips.usermanagement.entities.UserRestaurant;
import org.springframework.data.repository.CrudRepository;

public interface UserRestaurantRepository extends CrudRepository<UserRestaurant, Long> {
    UserRestaurant findByEmail(String email);

    UserRestaurant findByEmailAndPassword(String email, String password);
}
