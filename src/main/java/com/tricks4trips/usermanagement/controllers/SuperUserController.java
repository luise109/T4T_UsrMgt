package com.tricks4trips.usermanagement.controllers;

import com.tricks4trips.usermanagement.entities.SuperUser;
import com.tricks4trips.usermanagement.services.SuperUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/SUuser")
@CrossOrigin(origins = "*", methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT},allowedHeaders = "*")
public class SuperUserController {

    private final SuperUserService userService;

    public SuperUserController(SuperUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(SuperUser user){
        Map<String,Object> map = new HashMap<>();
        try {
            SuperUser tempUser = userService.createNewUser(user);
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
    public ResponseEntity<?> login(SuperUser user) {
        Map<String,Object> map = new HashMap<>();
        try {
            SuperUser tempUser = userService.login(user.getUsername(), user.getPassword());
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
    public ResponseEntity<?> modifyUser(String pass, String userUsername ,SuperUser userModify) {
        Map<String,Object> map = new HashMap<>();
        try {
            SuperUser tempUser = userService.modifyUser(pass, userUsername, userModify);
            if (tempUser == null) {
                map.put("message", "No se pudo modificar el usuario");
                map.put("code", false);
            }else {
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
    public ResponseEntity<?> deleteUser(String username, String password) {
        Map<String,Object> map = new HashMap<>();
        try {
            if (userService.deleteUser(username, password)) {
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