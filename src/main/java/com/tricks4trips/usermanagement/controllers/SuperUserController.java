package com.tricks4trips.usermanagement.controllers;

import com.tricks4trips.usermanagement.entities.SuperUser;
import com.tricks4trips.usermanagement.services.SuperUserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/SUuser")
@CrossOrigin(origins = "*", methods = {RequestMethod.DELETE,RequestMethod.GET},allowedHeaders = "*")
public class SuperUserController {

    private final SuperUserService userService;

    public SuperUserController(SuperUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public SuperUser createUser(SuperUser user) {
        return userService.createNewUser(user);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public SuperUser login(SuperUser user) {
        return userService.login(user.getCredential().getEmail(), user.getCredential().getPassword());
    }

}