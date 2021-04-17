package com.example.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.dao.UserDAO;
import com.example.entity.UserDetails;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.Date;

//https://www.infoworld.com/article/3543268/junit-5-tutorial-part-2-unit-testing-spring-mvc-with-junit-5.html

@SpringBootTest
public class UserServiceTest {
    
    @Autowired
    private UserService service;
    
    @MockBean
    private UserDAO repository;
    
    
    @Test
    @DisplayName("Test findById")
    
    void testFindById() {
       
         UserDetails user = new UserDetails(0,"a@gmail.com","1234",new Date(),new Date());
         doReturn(user).when(repository).findById(0);
         UserDetails userDetail = service.findById(0);
         Assertions.assertSame(userDetail, user, "UserDetail returned was not the same as the mock");
         verify(repository).findById(0);
    }

}
