package com.service.employee.services;

import com.service.employee.configuration.EmployeeException;
import com.service.employee.configuration.JwtTokenUtil;
import com.service.employee.dto.request.LoginRequest;
import com.service.employee.dto.response.LoginResponse;
import com.service.employee.dto.response.ResponseBody;
import com.service.employee.repository.UserAccountRepository;
import com.service.employee.utility.StringUtility;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
@AllArgsConstructor
public class AuthenticationService {

    final AuthenticationManager authenticationManager;
    final UserAccountRepository userAccountRepository;
    final JwtUserDetailService jwtUserDetailService;
    final JwtTokenUtil jwtTokenUtil;

    public ResponseBody<LoginResponse> loginWithUsernameAndPassword(LoginRequest request) throws NoSuchAlgorithmException {

        //verify existing user
        final var userDetails = jwtUserDetailService.loadUserByUsername(request.getUsername());
        var passwordValid = checkPassword(request, userDetails.getPassword());

        if (passwordValid) {
            // generate jwt token
            final String token = jwtTokenUtil.generateToken(userDetails);
            final LoginResponse bearer = new LoginResponse(token, "Bearer");
            var response = new ResponseBody<LoginResponse>();
            response.setSuccessResponse(bearer);
            return response;
        } else {
            throw new EmployeeException("Invalid username or password");
        }
    }

    private boolean checkPassword(LoginRequest request, String hashPasswordFromDB) throws NoSuchAlgorithmException {
        var hashPassword = StringUtility.hashPassword(request.getPassword());
        return hashPassword.equals(hashPasswordFromDB);
    }

}
