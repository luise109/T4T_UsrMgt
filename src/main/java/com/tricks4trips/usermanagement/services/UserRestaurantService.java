package com.tricks4trips.usermanagement.services;


import com.tricks4trips.usermanagement.entities.UserRestaurant;
import com.tricks4trips.usermanagement.repositories.UserRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRestaurantService {

    @Autowired
    private UserRestaurantRepository userRepository;




    public UserRestaurant createNewUser(UserRestaurant userRestaurant) {
        if (userRepository.findByEmail(userRestaurant.getEmail()) == null) {
            return userRepository.save(userRestaurant);
        }
        return null;
    }

    public UserRestaurant login(String email, String password) {
        if (email != null && password != null) {
            UserRestaurant userRestaurant = userRepository.findByEmailAndPassword(email, password);
            if (userRestaurant == null) {
                return null;
            }
            userRestaurant.setPassword("");
            return userRestaurant;
        }
        return null;
    }

}