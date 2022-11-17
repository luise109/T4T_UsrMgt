package com.tricks4trips.usermanagement.services;

import com.tricks4trips.usermanagement.Impl.UserDetailsImpl;
import com.tricks4trips.usermanagement.entities.User;
import com.tricks4trips.usermanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EncryptPassword encryptPassword;


    public User createNewUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) == null) {
            user.setPassword(encryptPassword.encrypt(user.getPassword()));
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    public User login(String email, String password) {
        if (email != null && password != null) {
            User user = userRepository.findByEmail(email);
            if (user != null) {
                if (encryptPassword.compare(password, user.getPassword())) {
                    return user;
                }
            }
        }
        return null;
    }
    public User modifyUser(String password, String email ,User userModify) {
        User user = this.login(email, password);
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

    public Boolean deleteUser(String email, String password) {
        User user = this.login(email, password);
        if (user != null) {
            userRepository.delete(user);
            return true;
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findOneByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new UserDetailsImpl(user);
    }
}
