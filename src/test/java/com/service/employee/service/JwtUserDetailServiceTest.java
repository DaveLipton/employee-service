package com.service.employee.service;

import com.service.employee.configuration.EmployeeException;
import com.service.employee.model.UserAccount;
import com.service.employee.repository.UserAccountRepository;
import com.service.employee.services.JwtUserDetailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class JwtUserDetailServiceTest {

    @InjectMocks
    JwtUserDetailService jwtUserDetailService;

    @Mock
    UserAccountRepository userAccountRepository;

    @Test
    void loadUsernameTest() {
        when(userAccountRepository.findByUsername(anyString())).thenReturn(UserAccount.builder().username("mock").password("password").build());
        var response = jwtUserDetailService.loadUserByUsername("mock");
        Assertions.assertNotNull(response);
    }

    @Test
    void loadUsernameFailTest() {
        when(userAccountRepository.findByUsername(anyString())).thenReturn(null);
        var response = assertThrowsExactly(EmployeeException.class, () ->
                jwtUserDetailService.loadUserByUsername("mock"));
        assertEquals("User not found with username", response.getMessage());
    }
}
