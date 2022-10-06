package com.service.employee.controller;

import com.service.employee.dto.request.LoginRequest;
import com.service.employee.services.AuthenticationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.security.NoSuchAlgorithmException;

@SpringBootTest
public class AuthenticationControllerTest {

    @InjectMocks
    AuthenticationController authenticationController;

    @Mock
    AuthenticationService authenticationService;

    @Test
    void loginWithUsernameAndPassword() throws NoSuchAlgorithmException {
        var response = authenticationController.loginWithUsernameAndPassword(LoginRequest.builder().username("mock").password("mock").build());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
