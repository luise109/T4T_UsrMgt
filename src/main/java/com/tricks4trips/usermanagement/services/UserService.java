package com.tricks4trips.usermanagement.services;

import com.tricks4trips.usermanagement.entities.User;
import com.tricks4trips.usermanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User createNewUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) == null) {
            return userRepository.save(user);
        }
        return null;
    }

    public User login(String email, String password) {
        if (email != null && password != null) {
            User user = userRepository.findByEmailAndPassword(email, password);
            if (user == null) {
                return null;
            }
            user.setPassword("");
            return user;
        }
        return null;
    }
    public User modifyUser(String password, String email ,User userModify) {
        User user = userRepository.findByEmailAndPassword(email, password);
        if (user != null) {
            userModify.setId(user.getId());
            if(userModify.getName() == null){
                userModify.setName(user.getName());
            }
            if(userModify.getEmail() == null){
                userModify.setEmail(user.getEmail());
            }
            if(userModify.getPassword() == null){
                userModify.setPassword(user.getPassword());
            }
            if(userModify.getPhone() == null){
                userModify.setPhone(user.getPhone());
            }
            if (userModify.getLastname() == null){
                userModify.setLastname(user.getLastname());
            }
            userRepository.save(userModify);
            userModify.setPassword("");
            return userModify;
        }
        return null;
    }

    public User deleteUser(String email, String password) {
        User user = userRepository.findByEmailAndPassword(email, password);
        if (user != null) {
            userRepository.delete(user);
            user = new User(password, email);
            return user;
        }
        return null;
    }

}
