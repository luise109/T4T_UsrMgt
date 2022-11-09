package com.tricks4trips.usermanagement.controllers;

import com.tricks4trips.usermanagement.entities.UserRestaurant;
import com.tricks4trips.usermanagement.services.UserRestaurantService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/userRestaurant")
@CrossOrigin(origins = "*", methods = {RequestMethod.DELETE, RequestMethod.GET},allowedHeaders = "*")
public class UserRestaurantController {

    private final UserRestaurantService userRestaurantService;

    public UserRestaurantController(UserRestaurantService userService) {
        this.userRestaurantService = userService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public UserRestaurant createUser(UserRestaurant user) {
        return userRestaurantService.createNewUser(user);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public UserRestaurant login(UserRestaurant user) {
        return userRestaurantService.login(user.getEmail(), user.getPassword());
    }

}