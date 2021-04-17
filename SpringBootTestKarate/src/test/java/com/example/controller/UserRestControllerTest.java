package com.example.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.entity.UserDetails;
import com.example.service.UserService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserRestControllerTest {
    
    @Autowired
    private TestRestTemplate template;
    
    @MockBean
    public UserService mockUserService;
    
    
    @Test
    public void findAll() {
        
        UserDetails user_1 = new UserDetails(0,"a@gmail.com","1234",new Date(),new Date());
        UserDetails user_2 = new UserDetails(1,"b@gmail.com","abcd",new Date(),new Date());
        
        List<UserDetails> userDetailsLst = new ArrayList<UserDetails>();
        userDetailsLst.add(user_1);
        userDetailsLst.add(user_2);
        
        when(mockUserService.findAll()).thenReturn(userDetailsLst);
        
        //List<UserDetails> ratings = Arrays.asList(restTemplate.getForObject("http://localhost:8084/ratingsdata/user/"+userId, UserDetails[].class));
        
        String getUrl = "/api/users";
        
        ParameterizedTypeReference<List<UserDetails>> responseType = new ParameterizedTypeReference<List<UserDetails>>() {};
        ResponseEntity<List<UserDetails>> resp = template.exchange(getUrl, HttpMethod.GET, null, responseType);
        List<UserDetails> list = resp.getBody();
        
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        
        assertEquals(2,list.size(), "data");
        assertEquals( "a@gmail.com", list.get(0).getEmail(), "email 0");
        assertEquals( "b@gmail.com", list.get(1).getEmail(), "email 1");
        
        verify(mockUserService).findAll();
        
    }
    
    
    @Test
    public void getUser() {
        
        UserDetails user_1 = new UserDetails(0,"a@gmail.com","1234",new Date(),new Date());
        
        when(mockUserService.findById(0)).thenReturn(user_1);
        
        String getUrl = "/api/users/0";
        
        ResponseEntity<UserDetails> entity = template.getForEntity(getUrl, UserDetails.class);
        
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        
        UserDetails det =  entity.getBody();
        assertEquals( "a@gmail.com", det.getEmail(), "email");
        assertEquals( "1234", det.getPassword(), "password");
        
        verify(mockUserService).findById(0);
        
    }
    
    

    
}
