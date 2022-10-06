package com.service.employee.dto.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {

    private final String jwtToken;
    private final String tokenType;

    public LoginResponse(String jwtToken, String tokenType) {
        this.jwtToken = jwtToken;
        this.tokenType = tokenType;
    }
}
