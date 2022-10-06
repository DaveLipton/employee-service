package com.service.employee.services;

import com.service.employee.configuration.EmployeeException;
import com.service.employee.repository.UserAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Objects;

@Component
@AllArgsConstructor
public class JwtUserDetailService implements UserDetailsService {

    final UserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userAccountRepository.findByUsername(username);
        if (Objects.nonNull(user)) {
            return new User(user.getUsername(), user.getPassword(),
                    new ArrayList<>());
        } else {
            throw new EmployeeException("User not found with username");
        }
    }
}
