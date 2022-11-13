package com.tricks4trips.usermanagement.controllers;

import com.tricks4trips.usermanagement.entities.UserAgency;
import com.tricks4trips.usermanagement.services.UserAgencyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userAgency")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST,RequestMethod.GET,RequestMethod.PUT,RequestMethod.DELETE},allowedHeaders = "*")
public class UserAgencyController {

    private final UserAgencyService userService;

    public UserAgencyController(UserAgencyService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(UserAgency user) {
        try {
            UserAgency tempUser = userService.createNewUser(user);
            if (tempUser == null) {
                return ResponseEntity.badRequest().body("No se pudo crear el usuario");
            } else {
                return ResponseEntity.ok(tempUser);
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().body("No se pudo crear el usuario");
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(UserAgency user) {
        try {
            UserAgency tempUser = userService.login(user.getEmail(), user.getPassword());
            if (tempUser == null) {
                return ResponseEntity.badRequest().body("No se pudo iniciar sesión, usuario o contraseña incorrectos");
            }else {
                return ResponseEntity.ok(tempUser);
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().body("No se pudo iniciar sesión, usuario o contraseña incorrectos");
        }
    }

    @RequestMapping(value = "/modify", method = RequestMethod.PUT)
    public ResponseEntity<?> modifyUser(String pass, String userEmail ,UserAgency userModify) {
        try {
            UserAgency tempUser = userService.modifyUser(pass, userEmail, userModify);
            if (tempUser == null) {
                return ResponseEntity.badRequest().body("No se pudo modificar el usuario");
            }else {
                return ResponseEntity.ok(tempUser);
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().body("No se pudo modificar el usuario");
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(String password, String email) {
        try {
            if (userService.deleteUser(password, email)) {
                return ResponseEntity.ok(true);
            } else {
                return ResponseEntity.badRequest().body("No se pudo eliminar el usuario");
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().body("No se pudo eliminar el usuario");
        }
    }

}