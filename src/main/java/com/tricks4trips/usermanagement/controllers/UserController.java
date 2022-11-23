package com.tricks4trips.usermanagement.controllers;

import com.tricks4trips.usermanagement.entities.User;
import com.tricks4trips.usermanagement.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        Map<String,Object> map = new HashMap<>();
        try {
            User tempUser = userService.emailExists(user.getEmail());
            if (tempUser != null) {
                map.put("message", "No se pudo crear el usuario");
                map.put("code", false);
            } else {
                if (this.allFieldsAreValid(user).equals("true")) {
                    userService.createNewUser(user);
                    map.put("message", "Usuario creado correctamente");
                    map.put("code", true);
                    map.put("user", user);
                } else {
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
    public ResponseEntity<?> login(User user) {
        Map<String,Object> map = new HashMap<>();
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
        Map<String,Object> map = new HashMap<>();
        try {
            User tempUser = userService.emailExists(userEmail);
            if (tempUser != null) {
                if (this.allFieldsAreValid(userModify).equals("true"))
                {
                    tempUser = userService.modifyUser(pass, userEmail, userModify);
                    if (tempUser != null) {
                        map.put("message", "Usuario modificado correctamente");
                        map.put("code", true);
                        map.put("user",tempUser);
                    }
                    else {
                        map.put("message", "No se pudo modificar el usuario");
                        map.put("code", false);
                    }
                } else {
                    map.put("message", this.allFieldsAreValid(userModify));
                    map.put("code", false);
                }
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

    private boolean isEmailValid(String email) {
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(email);
        return mather.find();
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
        return !mather.find();
    }

    private boolean isPhoneValid(String phone) {
        Pattern pattern = Pattern
                .compile("^[0-9]{10}$");
        Matcher mather = pattern.matcher(phone);
        return mather.find();
    }

    private String allFieldsAreValid(User user) {
        try {
            if(!this.isEmailValid(user.getEmail())) {
                return "Email inválido";
            }
            if(!this.isPasswordValid(user.getPassword())) {
                return "Contraseña inválida";
            }
            if(!this.isPhoneValid(user.getPhone())) {
                return "Teléfono inválido";
            }
            if(this.isNameValid(user.getName())) {
                return "Nombre inválido";
            }
            if(this.isNameValid(user.getLastname())) {
                return "Apellido inválido";
            }
        }catch (Exception e) {
            return "true";
        }
        return "true";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(String email, String password) {
        Map<String,Object> map = new HashMap<>();
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

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity<?> getUserbyEmail(@RequestParam String email) {
        Map<String,Object> map = new HashMap<>();
        try {
            User user = userService.emailExists(email);
            if(user != null) {
                map.put("Message", "Usuario");
                map.put("user", user);
                map.put("code",true);
            } else {
              map.put("Message", "Usuario no encontrado");
              map.put("code",false);
            }
            return ResponseEntity.ok(map);
        }catch (Exception e) {
            map.put("Message","Ha ocurrido un error al buscar el usuario");
            map.put("code", false);
            return ResponseEntity.badRequest().body(map);
        }
    }

}
