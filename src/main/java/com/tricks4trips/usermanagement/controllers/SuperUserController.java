package com.tricks4trips.usermanagement.controllers;

import com.tricks4trips.usermanagement.entities.SuperUser;
import com.tricks4trips.usermanagement.services.SuperUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        try {
            SuperUser tempUser = userService.createNewUser(user);
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
    public ResponseEntity<?> login(SuperUser user) {
        try {
            SuperUser tempUser = userService.login(user.getUsername(), user.getPassword());
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
    public ResponseEntity<?> modifyUser(String pass, String userUsername ,SuperUser userModify) {
        try {
            SuperUser tempUser = userService.modifyUser(pass, userUsername, userModify);
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
    public ResponseEntity<?> deleteUser(String username, String password) {
        try {
            if (userService.deleteUser(username, password)) {
                return ResponseEntity.ok(true);
            } else {
                return ResponseEntity.badRequest().body("No se pudo eliminar el usuario");
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().body("No se pudo eliminar el usuario");
        }
    }


}