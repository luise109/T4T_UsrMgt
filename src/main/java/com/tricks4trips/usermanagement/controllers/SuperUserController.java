package com.tricks4trips.usermanagement.controllers;

import com.tricks4trips.usermanagement.entities.SuperUser;
import com.tricks4trips.usermanagement.services.SuperUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            SuperUser tempUser = userService.userExist(user.getUsername());
            if (tempUser != null) {
                map.put("message", "No se pudo crear el usuario");
                map.put("code", false);
            } else {
                if (this.allFieldsAreValid(user).equals("true")) {
                    userService.createNewUser(user);
                    map.put("message", "Usuario creado correctamente");
                    map.put("code", true);
                    map.put("user", user);
                }
                else {
                    map.put("message", this.allFieldsAreValid(user));
                    map.put("code", false);
                }
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
            SuperUser tempUser = userService.userExist(userUsername);
            if (tempUser == null) {
                map.put("message", "No se pudo modificar el usuario");
                map.put("code", false);
            }else {
                if (this.allFieldsAreValid(userModify).equals("true"))
                {
                    tempUser = userService.modifyUser(pass, userUsername, userModify);
                    if (tempUser == null) {
                        map.put("message", "No se pudo modificar el usuario, contraseña incorrecta");
                        map.put("code", false);
                    }else {
                        map.put("message", "Usuario modificado correctamente");
                        map.put("code", true);
                        map.put("user", userModify);
                    }
                }
                else {
                    map.put("message", this.allFieldsAreValid(userModify));
                    map.put("code", false);
                }

            }
            return ResponseEntity.ok(map);
        }catch (Exception e){
            map.put("message", "No se pudo modificar el usuario");
            map.put("code", false);
            return ResponseEntity.badRequest().body(map);
        }
    }

    private boolean isPasswordValid(String password) {
        Pattern pattern = Pattern
                .compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$");
        Matcher mather = pattern.matcher(password);
        return mather.find();
    }

    private boolean isNameValid(String name) {
        Pattern pattern = Pattern
                .compile("^[a-zA-Z ]*$");
        Matcher mather = pattern.matcher(name);
        return mather.find();
    }

    private String allFieldsAreValid(SuperUser user) {
        try {
            if (!this.isPasswordValid(user.getPassword())) {
                return "Contraseña inválida";
            }
        }catch (Exception ignored) {
        }

        try {
            if(!this.isNameValid(user.getName())) {
                return "Nombre inválido";
            }
        }catch (Exception ignored) {
        }

        try {
            if(!this.isNameValid(user.getLastname())) {
                return "Apellido inválido";
            }
        }catch (Exception ignored) {
        }
        return "true";
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