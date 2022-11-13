package com.tricks4trips.usermanagement.services;

import com.tricks4trips.usermanagement.entities.UserAgency;
import com.tricks4trips.usermanagement.repositories.UserAgencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAgencyService {

    @Autowired
    private UserAgencyRepository userRepository;


    public UserAgency createNewUser(UserAgency user) {
        if (userRepository.findByEmail(user.getEmail()) == null) {
            return userRepository.save(user);
        }
        return null;
    }

    public UserAgency login(String email, String password) {
        if (email != null && password != null) {
            UserAgency userAgency =  userRepository.findByEmailAndPassword(email, password);
            if (userAgency == null) {
                return null;
            }
            userAgency.setPassword("");
            return userAgency;
        }
        return null;
    }

    public UserAgency modifyUser(String password, String email ,UserAgency userModify) {
        UserAgency user = userRepository.findByEmailAndPassword(email, password);
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

    public Boolean deleteUser(String password, String email) {
        UserAgency user = userRepository.findByEmailAndPassword(email, password);
        if (user != null) {
            userRepository.delete(user);
            return true;
        }
        return false;
    }

}