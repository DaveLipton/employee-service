package com.service.employee.controller;

import com.service.employee.dto.request.LoginRequest;
import com.service.employee.dto.response.LoginResponse;
import com.service.employee.dto.response.ResponseBody;
import com.service.employee.services.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@AllArgsConstructor
public class AuthenticationController {

    final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<ResponseBody<LoginResponse>> loginWithUsernameAndPassword(@RequestBody LoginRequest request) throws NoSuchAlgorithmException {
        return ResponseEntity.ok(authenticationService.loginWithUsernameAndPassword(request));
    }
}
