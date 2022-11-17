package com.tricks4trips.usermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class UserManagementApplication {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger("UserManagementApplication");
        SpringApplication.run(UserManagementApplication.class, args);
        logger.info("La aplicacion se ha iniciado correctamente");
    }
//spring.datasource.url=jdbc:mysql://database-1.cizpottg01n2.us-east-1.rds.amazonaws.com:3306/usrMgt
    //spring.datasource.username=admin
    //spring.datasource.password=password
}