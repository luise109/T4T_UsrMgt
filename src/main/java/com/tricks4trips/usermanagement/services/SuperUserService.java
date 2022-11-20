package com.tricks4trips.usermanagement.services;

import com.tricks4trips.usermanagement.Impl.UserDetailsImpl;
import com.tricks4trips.usermanagement.entities.SuperUser;
import com.tricks4trips.usermanagement.repositories.SuperUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SuperUserService implements UserDetailsService {

    @Autowired
    private SuperUserRepository userRepository;
    @Autowired
    private EncryptPassword encryptPassword;

    public SuperUser userExist(String email) {
        return userRepository.findByUsername(email);
    }


    public SuperUser createNewUser(SuperUser user) {
        if (userRepository.findByUsername(user.getUsername()) == null) {
            user.setPassword(encryptPassword.encrypt(user.getPassword()));
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    public SuperUser login(String username, String password) {
        if (username != null && password != null) {
            SuperUser superUser = userRepository.findByUsername(username);
            if (superUser != null) {
                if (encryptPassword.compare(password, superUser.getPassword())) {
                    return superUser;
                }
            }
        }
        return null;
    }

    public SuperUser modifyUser(String password, String username ,SuperUser userModify) {
        SuperUser user = this.login(username, password);
        if (user != null) {
            userModify.setId(user.getId());
            if (userModify.getPassword() == null) {
                userModify.setPassword(user.getPassword());
            } else {
                userModify.setPassword(encryptPassword.encrypt(userModify.getPassword()));
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
            return userModify;
        }
        return null;
    }

    public Boolean deleteUser(String username, String password) {
        SuperUser user = this.login(username, password);
        if (user != null) {
            userRepository.delete(user);
            return true;
        }
        return false;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SuperUser user = userRepository.findOneByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new UserDetailsImpl(user);
    }

}