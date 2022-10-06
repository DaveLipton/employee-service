package com.service.employee.service;

import com.service.employee.configuration.EmployeeException;
import com.service.employee.configuration.JwtTokenUtil;
import com.service.employee.dto.request.LoginRequest;
import com.service.employee.repository.UserAccountRepository;
import com.service.employee.services.AuthenticationService;
import com.service.employee.services.JwtUserDetailService;
import com.service.employee.utility.StringUtility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AuthenticationServiceTest {

    @InjectMocks
    AuthenticationService authenticationService;

    @Mock
    AuthenticationManager authenticationManager;
    @Mock
    UserAccountRepository userAccountRepository;
    @Mock
    JwtUserDetailService jwtUserDetailService;
    @Mock
    JwtTokenUtil jwtTokenUtil;

    @Test
    void loginTest() throws NoSuchAlgorithmException {
        when(jwtUserDetailService.loadUserByUsername(anyString())).thenReturn(new User("mock", StringUtility.hashPassword("password"),
                new ArrayList<>()));
        when(jwtTokenUtil.generateToken(any())).thenReturn("token");
        var response = authenticationService.loginWithUsernameAndPassword(LoginRequest.builder()
                .username("mock")
                .password("password").build());

        Assertions.assertNotNull(response.getResultData());
    }

    @Test
    void loginFailPasswordIncorrectTest() {

        when(jwtUserDetailService.loadUserByUsername(anyString())).thenReturn(new User("mock", "password",
                new ArrayList<>()));
        when(jwtTokenUtil.generateToken(any())).thenReturn("token");

        var response = assertThrowsExactly(EmployeeException.class, () ->
                authenticationService.loginWithUsernameAndPassword(LoginRequest.builder()
                        .username("mock")
                        .password("password").build()));
        Assertions.assertEquals("Invalid username or password", response.getMessage());

    }

    @Test
    void loginFailNotFoundUsernameTest() {
        when(jwtUserDetailService.loadUserByUsername(anyString())).thenThrow(NullPointerException.class);
        var response = assertThrowsExactly(NullPointerException.class, () ->
                authenticationService.loginWithUsernameAndPassword(LoginRequest.builder()
                        .username("mock")
                        .password("password").build()));
        Assertions.assertEquals(null, response.getMessage());

    }
}
