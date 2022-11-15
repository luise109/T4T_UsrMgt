package com.tricks4trips.usermanagement.controllers;

import com.tricks4trips.usermanagement.entities.User;
import com.tricks4trips.usermanagement.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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
        Map<String,Object> map = new HashMap();
        try {
            User userCreated = userService.createNewUser(user);
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
    public ResponseEntity<?> login(User user) {
        Map<String,Object> map = new HashMap();
        try {
            User userLogin = userService.login(user.getEmail(), user.getPassword());
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
            map.put("message", "No se pudo iniciar sesión, usuario o contraseña incorrectos");
            map.put("code", false);
            return ResponseEntity.badRequest().body(map);
        }
    }

    @RequestMapping(value = "/modify", method = RequestMethod.PUT)
    public ResponseEntity<?> modifyUser(String pass, String userEmail ,User userModify) {
        Map<String,Object> map = new HashMap();
        try {
            User user = userService.modifyUser(pass, userEmail, userModify);
            if (user != null) {
                map.put("message", "Usuario modificado correctamente");
                map.put("code", true);
                map.put("user", user);
            } else {
                map.put("message", "No se pudo modificar el usuario");
                map.put("code", false);
            }
            return ResponseEntity.ok(map);
        }catch (Exception e){
            map.put("message", "No se pudo modificar el usuario");
            map.put("code", false);
            return ResponseEntity.badRequest().body(map);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(String email, String password) {
        Map<String,Object> map = new HashMap();
        try {
            boolean deleted = userService.deleteUser(email, password);
            if (deleted) {
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
