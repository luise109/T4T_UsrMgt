package com.tricks4trips.usermanagement.controllers;

import com.tricks4trips.usermanagement.entities.User;
import com.tricks4trips.usermanagement.services.UserService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> createUser(User user) {
        try {
            User userCreated = userService.createNewUser(user);
            if (userCreated != null) {
                return ResponseEntity.ok(userCreated);
            }else {
                return ResponseEntity.badRequest().body("No se pudo crear el usuario");
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().body("No se pudo crear el usuario");
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(User user) {
        try {
            User userLogin = userService.login(user.getEmail(), user.getPassword());
            if (userLogin != null) {
                return ResponseEntity.ok(userLogin);
            } else {
                return ResponseEntity.badRequest().body("No se pudo iniciar sesión, verifique sus credenciales");
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().body("No se pudo iniciar sesión, verifique sus credenciales");
        }
    }

    @RequestMapping(value = "/modify", method = RequestMethod.PUT)
    public ResponseEntity<?> modifyUser(String pass, String userEmail ,User userModify) {
        try {
            User user = userService.modifyUser(pass, userEmail, userModify);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.badRequest().body("No se pudo modificar el usuario");
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().body("No se pudo modificar el usuario");
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(String email, String password) {
        try {
            if (userService.deleteUser(email, password)) {
                return ResponseEntity.ok(true);
            } else {
                return ResponseEntity.badRequest().body("No se pudo eliminar el usuario");
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().body("No se pudo eliminar el usuario");
        }
    }

}
