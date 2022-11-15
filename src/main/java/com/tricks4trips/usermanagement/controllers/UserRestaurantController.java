package com.tricks4trips.usermanagement.controllers;

import com.tricks4trips.usermanagement.entities.UserRestaurant;
import com.tricks4trips.usermanagement.services.UserRestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


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
        Map<String,Object> map = new HashMap<>();
        try {
            UserRestaurant userCreated = userRestaurantService.createNewUser(user);
            if (userCreated == null) {
                map.put("message", "No se pudo crear el usuario");
                map.put("code", false);
            } else {
                map.put("message", "Usuario creado correctamente");
                map.put("code", true);
                map.put("user", userCreated);
            }
            return ResponseEntity.ok(map);
        }catch (Exception e){
            map.put("message", "No se pudo crear el usuario");
            map.put("code", false);
            return ResponseEntity.badRequest().body(map);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(UserRestaurant user) {
        Map<String,Object> map = new HashMap<>();
        try {
            UserRestaurant userLogin = userRestaurantService.login(user.getEmail(), user.getPassword());
            if (userLogin != null) {
                map.put("message", "Sesión iniciada correctamente");
                map.put("code", true);
                map.put("user", userLogin);
            } else {
                map.put("message", "No se pudo iniciar sesión, usuario o contraseña incorrectos");
                map.put("code", false);
            }
            return ResponseEntity.ok(map);
        }catch (Exception e){
            map.put("message", "No se pudo iniciar sesión");
            map.put("code", false);
            return ResponseEntity.badRequest().body(map);
        }
    }

    @RequestMapping(value = "/modify", method = RequestMethod.PUT)
    public ResponseEntity<?> modifyUser(String pass, String userEmail, UserRestaurant userModify) {
        Map<String,Object> map = new HashMap<>();
        try {
            UserRestaurant user = userRestaurantService.modifyUser(pass, userEmail, userModify);
            if (user == null) {
                map.put("message", "No se pudo modificar el usuario");
                map.put("code", false);
            } else {
                map.put("message", "Usuario modificado correctamente");
                map.put("code", true);
                map.put("user", user);
            }
            return ResponseEntity.ok(map);
        }catch (Exception e){
            map.put("message", "No se pudo modificar el usuario");
            map.put("code", false);
            return ResponseEntity.badRequest().body(map);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(String password, String email) {
        Map<String,Object> map = new HashMap<>();
        try {
            boolean userDeleted = userRestaurantService.deleteUser(password, email);
            if (userDeleted) {
                map.put("message", "Usuario eliminado correctamente");
                map.put("code", true);
            } else {
                map.put("message", "No se pudo eliminar el usuario");
                map.put("code", false);
            }
            return ResponseEntity.ok(map);
        }catch (Exception e){
            map.put("message", "No se pudo eliminar el usuario");
            map.put("code", false);
            return ResponseEntity.badRequest().body(map);
        }
    }

}
