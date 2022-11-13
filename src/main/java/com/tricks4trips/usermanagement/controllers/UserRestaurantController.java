package com.tricks4trips.usermanagement.controllers;

import com.tricks4trips.usermanagement.entities.UserRestaurant;
import com.tricks4trips.usermanagement.services.UserRestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/userRestaurant")
@CrossOrigin(origins = "*", methods = {RequestMethod.DELETE, RequestMethod.GET,RequestMethod.PUT,RequestMethod.POST},allowedHeaders = "*")
public class UserRestaurantController {

    private final UserRestaurantService userRestaurantService;

    public UserRestaurantController(UserRestaurantService userService) {
        this.userRestaurantService = userService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(UserRestaurant user) {
        try {
            UserRestaurant userCreated = userRestaurantService.createNewUser(user);
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
    public ResponseEntity<?> login(UserRestaurant user) {
        try {
            UserRestaurant userLogin = userRestaurantService.login(user.getEmail(), user.getPassword());
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
    public ResponseEntity<?> modifyUser(String pass, String userEmail, UserRestaurant userModify) {
        try {
            UserRestaurant user = userRestaurantService.modifyUser(pass, userEmail, userModify);
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
    public ResponseEntity<?> deleteUser(String password, String email) {
        try {
            if (userRestaurantService.deleteUser(password, email)) {
                return ResponseEntity.ok(true);
            } else {
                return ResponseEntity.badRequest().body("No se pudo eliminar el usuario");
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().body("No se pudo eliminar el usuario");
        }
    }

}
