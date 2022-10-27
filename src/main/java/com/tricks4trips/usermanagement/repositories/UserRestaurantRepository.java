package com.tricks4trips.usermanagement.repositories;


import com.tricks4trips.usermanagement.entities.UserRestaurant;
import org.springframework.data.repository.CrudRepository;

public interface UserRestaurantRepository extends CrudRepository<UserRestaurant, Long> {
    UserRestaurant findByCredential_Id(Long id);
}
