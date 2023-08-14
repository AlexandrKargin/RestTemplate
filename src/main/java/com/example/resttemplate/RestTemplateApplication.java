package com.example.resttemplate;


import com.example.resttemplate.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class RestTemplateApplication {

    public static void main(String[] args) {

        SpringApplication.run(RestTemplateApplication.class, args);

        String sessionId = UserApiService.getSessionId();

        User createUser = new User(3L, "James", "Brown", (byte) 24);
        User updateUser = new User(3L, "Tomas", "Shelby", (byte) 24);

        System.out.println(UserApiService.createUser(createUser, sessionId) +
                UserApiService.updateUser(updateUser, sessionId) +
                UserApiService.deleteUser(3L, sessionId));
    }



}
