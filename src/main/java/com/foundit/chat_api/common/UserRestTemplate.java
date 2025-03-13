package com.foundit.chat_api.common;

import com.foundit.chat_api.user.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserRestTemplate {
    private final RestTemplate restTemplate;

    public UserRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public User getUser(Long userId) {
        String url = "https://localhost:8081/user/getuserbyid?userId="+userId;
        return restTemplate.getForObject(url, User.class);
    }

    public User getUserByEmail(String email) {
        String url = "https://localhost:8081/user/getuserbyemail?email="+email;
        return restTemplate.getForObject(url,User.class);
    }

    public User getUserByUserId(String userId) {
        String url = "https://localhost:8081/user/getuserbyuserid?email="+userId;
        return restTemplate.getForObject(url,User.class);
    }
}
