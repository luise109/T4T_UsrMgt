package com.tricks4trips.usermanagement.services;

import com.tricks4trips.usermanagement.Impl.UserDetailsImpl;
import com.tricks4trips.usermanagement.entities.UserAgency;
import com.tricks4trips.usermanagement.repositories.UserAgencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAgencyService implements UserDetailsService {

    @Autowired
    private UserAgencyRepository userRepository;
    @Autowired
    private EncryptPassword encryptPassword;


    public UserAgency createNewUser(UserAgency user) {
        if (userRepository.findByEmail(user.getEmail()) == null) {
            user.setPassword(encryptPassword.encrypt(user.getPassword()));
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    public UserAgency login(String email, String password) {
        if (email != null && password != null) {
            UserAgency userAgency =  userRepository.findByEmail(email);
            if (userAgency != null) {
                if (encryptPassword.compare(password, userAgency.getPassword())) {
                    return userAgency;
                }
            }
        }
        return null;
    }

    public UserAgency modifyUser(String password, String email ,UserAgency userModify) {
        UserAgency user = this.login(email, password);
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
        UserAgency user = this.login(email, password);
        if (user != null) {
            userRepository.delete(user);
            return true;
        }
        return false;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAgency user = userRepository.findOneByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new UserDetailsImpl(user);
    }

}