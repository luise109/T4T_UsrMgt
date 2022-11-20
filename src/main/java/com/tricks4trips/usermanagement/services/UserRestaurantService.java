package com.tricks4trips.usermanagement.services;


import com.tricks4trips.usermanagement.Impl.UserDetailsImpl;
import com.tricks4trips.usermanagement.entities.UserRestaurant;
import com.tricks4trips.usermanagement.repositories.UserRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserRestaurantService implements UserDetailsService {

    @Autowired
    private UserRestaurantRepository userRepository;
    @Autowired
    private EncryptPassword encryptPassword;


    public UserRestaurant emailExists(String email) {
        return userRepository.findByEmail(email);
    }

    public UserRestaurant createNewUser(UserRestaurant userRestaurant) {
        if (userRepository.findByEmail(userRestaurant.getEmail()) == null) {
            userRestaurant.setPassword(encryptPassword.encrypt(userRestaurant.getPassword()));
            return userRepository.save(userRestaurant);
        } else {
            return null;
        }
    }

    public UserRestaurant login(String email, String password) {
        if (email != null && password != null) {
            UserRestaurant userRestaurant = userRepository.findByEmail(email);
            if (userRestaurant != null) {
                if (encryptPassword.compare(password, userRestaurant.getPassword())) {
                    return userRestaurant;
                }
            }
        }
        return null;
    }

    public UserRestaurant modifyUser(String password, String email ,UserRestaurant userModify) {
        UserRestaurant user = this.login(email, password);
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
            } else {
                userModify.setPassword(encryptPassword.encrypt(userModify.getPassword()));
            }
            if(userModify.getPhone() == null){
                userModify.setPhone(user.getPhone());
            }
            if(userModify.getAddress() == null){
                userModify.setAddress(user.getAddress());
            }
            if(userModify.getRfc() == null){
                userModify.setRfc(user.getRfc());
            }
            if(userModify.getZip() == null){
                userModify.setZip(user.getZip());
            }
            if(userModify.getWebPage() == null){
                userModify.setWebPage(user.getWebPage());
            }
            userRepository.save(userModify);
            return userModify;
        }
        return null;
    }

    public Boolean deleteUser(String password, String email) {
        UserRestaurant user = this.login(email, password);
        if (user != null) {
            userRepository.delete(user);
            return true;
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserRestaurant user = userRepository.findOneByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new UserDetailsImpl(user);
    }

}