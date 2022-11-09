package com.tricks4trips.usermanagement.services;


import com.tricks4trips.usermanagement.entities.UserRestaurant;
import com.tricks4trips.usermanagement.repositories.UserRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRestaurantService {

    @Autowired
    private UserRestaurantRepository userRepository;




    public UserRestaurant createNewUser(UserRestaurant userRestaurant) {
        if (userRepository.findByEmail(userRestaurant.getEmail()) == null) {
            return userRepository.save(userRestaurant);
        }
        return null;
    }

    public UserRestaurant login(String email, String password) {
        if (email != null && password != null) {
            UserRestaurant userRestaurant = userRepository.findByEmailAndPassword(email, password);
            if (userRestaurant == null) {
                return null;
            }
            userRestaurant.setPassword("");
            return userRestaurant;
        }
        return null;
    }

    public UserRestaurant modifyUser(String password, String email ,UserRestaurant userModify) {
        UserRestaurant user = userRepository.findByEmailAndPassword(email, password);
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
            userModify.setPassword("");
            return userModify;
        }
        return null;
    }

    public UserRestaurant deleteUser(String email, String password) {
        UserRestaurant user = userRepository.findByEmailAndPassword(email, password);
        if (user != null) {
            userRepository.delete(user);
            user = new UserRestaurant(password, email);
            return user;
        }
        return null;
    }

}