package com.service.employee.dto.request;

import lombok.*;

@Data
@Builder
public class LoginRequest {

    private String username;
    private String password;
}
