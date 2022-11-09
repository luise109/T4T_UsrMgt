package com.tricks4trips.usermanagement.controllers;

import com.tricks4trips.usermanagement.entities.User;
import com.tricks4trips.usermanagement.services.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT},allowedHeaders = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public User createUser(User user) {
        return userService.createNewUser(user);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User login(User user) {
        return userService.login(user.getEmail(), user.getPassword());
    }

    @RequestMapping(value = "/modify", method = RequestMethod.PUT)
    public User modifyUser(String pass, String userEmail ,User userModify) {
        return userService.modifyUser(pass, userEmail, userModify);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public User deleteUser(String email, String password) {
        return userService.deleteUser(email, password);
    }

}
