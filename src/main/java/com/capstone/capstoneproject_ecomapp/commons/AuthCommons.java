package com.capstone.capstoneproject_ecomapp.commons;

import com.capstone.capstoneproject_ecomapp.dtos.UserDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthCommons {
    private long id;
    private String name;
    private String email;

    private RestTemplate restTemplate;

    public AuthCommons(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDto validateToken(String token) {
        ResponseEntity<UserDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/auth/validate/" + token,
                UserDto.class);
        return responseEntity.getBody();
    }
}
