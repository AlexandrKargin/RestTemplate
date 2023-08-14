package com.example.resttemplate;

import com.example.resttemplate.model.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class UserApiService {

    private static final String URL = "http://94.198.50.185:7081/api/users";
    private static final RestTemplate restTemplate = new RestTemplate();

    public static String getSessionId() {
        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {
                });
        return responseEntity.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
    }

    public static String createUser(User user, String sessionId) {
        HttpHeaders httpHeaders = createHeadersWithSessionId(sessionId);
        HttpEntity<User> requestEntity = new HttpEntity<>(user, httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL, requestEntity, String.class);
        return responseEntity.getBody();
    }

    public static String updateUser(User updateUser, String sessionId) {
        HttpHeaders httpHeaders = createHeadersWithSessionId(sessionId);
        HttpEntity<User> requestEntity = new HttpEntity<>(updateUser, httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.PUT, requestEntity, String.class);
        return responseEntity.getBody();
    }

    public static String deleteUser(Long id, String sessionId) {
        HttpHeaders httpHeaders = createHeadersWithSessionId(sessionId);
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL + "/" + id, HttpMethod.DELETE, requestEntity, String.class);
        return responseEntity.getBody();
    }

    private static HttpHeaders createHeadersWithSessionId(String sessionId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.COOKIE, sessionId);
        return httpHeaders;
    }
}
