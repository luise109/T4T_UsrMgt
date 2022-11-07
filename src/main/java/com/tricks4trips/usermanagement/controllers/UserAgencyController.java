package com.tricks4trips.usermanagement.controllers;

import com.tricks4trips.usermanagement.entities.UserAgency;
import com.tricks4trips.usermanagement.services.UserAgencyService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userAgency")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST,RequestMethod.GET},allowedHeaders = "*")
public class UserAgencyController {

    private final UserAgencyService userService;

    public UserAgencyController(UserAgencyService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public UserAgency createUser(UserAgency user) {
        return userService.createNewUser(user);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public UserAgency login(UserAgency user) {
        return userService.login(user.getEmail(), user.getPassword());
    }

}