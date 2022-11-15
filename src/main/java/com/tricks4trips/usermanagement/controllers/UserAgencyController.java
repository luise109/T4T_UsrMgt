package com.tricks4trips.usermanagement.controllers;

import com.tricks4trips.usermanagement.entities.UserAgency;
import com.tricks4trips.usermanagement.services.UserAgencyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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
        Map<String,Object> map = new HashMap<>();
        try {
            UserAgency tempUser = userService.createNewUser(user);
            if (tempUser == null) {
                map.put("message", "No se pudo crear el usuario");
                map.put("code", false);
            } else {
                map.put("message", "Usuario creado correctamente");
                map.put("code", true);
                map.put("user", tempUser);
            }
            return ResponseEntity.ok(map);
        }catch (Exception e){
            map.put("message", "No se pudo crear el usuario");
            map.put("code", false);
            return ResponseEntity.badRequest().body(map);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(UserAgency user) {
        Map<String,Object> map = new HashMap<>();
        try {
            UserAgency tempUser = userService.login(user.getEmail(), user.getPassword());
            if (tempUser == null) {
                map.put("message", "No se pudo iniciar sesión, usuario o contraseña incorrectos");
                map.put("code", false);
            }else {
                map.put("message", "Sesión iniciada correctamente");
                map.put("code", true);
                map.put("user", tempUser);
            }
            return ResponseEntity.ok(map);
        }catch (Exception e){
            map.put("message", "No se pudo iniciar sesión, usuario o contraseña incorrectos");
            map.put("code", false);
            return ResponseEntity.badRequest().body(map);
        }
    }

    @RequestMapping(value = "/modify", method = RequestMethod.PUT)
    public ResponseEntity<?> modifyUser(String pass, String userEmail ,UserAgency userModify) {
        Map<String,Object> map = new HashMap<>();
        try {
            UserAgency tempUser = userService.modifyUser(pass, userEmail, userModify);
            if (tempUser == null) {
                map.put("message", "No se pudo modificar el usuario");
                map.put("code", false);
            } else {
                map.put("message", "Usuario modificado correctamente");
                map.put("code", true);
                map.put("user", tempUser);
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
            boolean tempUser = userService.deleteUser(password, email);
            if (tempUser) {
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