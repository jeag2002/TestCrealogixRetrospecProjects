package com.example.controller;

//https://www.adictosaltrabajo.com/2017/05/11/prueba-tus-api-rest-con-rest-assured/

import io.restassured.RestAssured;
import org.junit.Test;

import com.example.entity.UserDetails;

import static org.hamcrest.Matchers.*;

import java.util.List;


public class UserRestControllerTest {
    
    @Test
    public void processFindAll() {
        
        List<UserDetails> returnData = 
                RestAssured
                .given()
                    .baseUri("http://localhost:8080")
                    .and()
                    .contentType("application/json")
                    .log().all()
                .when()
                    .get("/api/users")
                .then()
                    .log().all()
                    .assertThat().statusCode(is(equalTo(200)))
                    .and().extract().jsonPath().getList(".", UserDetails.class);
    }
    
    
    @Test
    public void processFindUser() {
        
        UserDetails returnData =
                RestAssured
                .given()
                    .baseUri("http://localhost:8080")
                    .and()
                    .contentType("application/json")
                    .log().all()
                 .when()
                     .get("/api/users/0")
                 .then()
                     .log().all()
                     .assertThat().statusCode(is(equalTo(200)))
                     .and()
                     .body("id",equalTo(0))
                     .and().extract().body().as(UserDetails.class);
                     
                     
                
        
    }

}
