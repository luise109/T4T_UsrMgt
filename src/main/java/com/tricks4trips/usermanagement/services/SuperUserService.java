package com.tricks4trips.usermanagement.services;

import com.tricks4trips.usermanagement.entities.SuperUser;
import com.tricks4trips.usermanagement.repositories.SuperUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuperUserService {

    @Autowired
    private SuperUserRepository userRepository;

    public SuperUser createNewUser(SuperUser user) {
        if (userRepository.findByUsername(user.getUsername()) == null) {
            return userRepository.save(user);
        }
        return null;
    }

    public SuperUser login(String email, String password) {
        if (email != null && password != null) {
            SuperUser superUser = userRepository.findByUsernameAndPassword(email, password);
            if (superUser == null) {
                return null;
            }
            superUser.setPassword("");
            return superUser;
        }
        return null;
    }

    public SuperUser modifyUser(String password, String username ,SuperUser userModify) {
        SuperUser user = userRepository.findByUsernameAndPassword(username, password);
        if (user != null) {
            userModify.setId(user.getId());
            if (userModify.getPassword() == null) {
                userModify.setPassword(user.getPassword());
            }
            if(userModify.getUsername() == null){
                userModify.setUsername(user.getUsername());
            }
            if(userModify.getName() == null){
                userModify.setName(user.getName());
            }
            if(userModify.getLastname() == null){
                userModify.setLastname(user.getLastname());
            }
            userRepository.save(userModify);
            userModify.setPassword("");
            return userModify;
        }
        return null;
    }

    public Boolean deleteUser(String username, String password) {
        SuperUser user = userRepository.findByUsernameAndPassword(username, password);
        if (user != null) {
            userRepository.delete(user);
            return true;
        }
        return false;
    }

}