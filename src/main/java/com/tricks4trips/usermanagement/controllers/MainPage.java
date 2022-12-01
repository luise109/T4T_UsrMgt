package com.tricks4trips.usermanagement.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainPage {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage() {
        return "Welcome to the main page";
    }

}
