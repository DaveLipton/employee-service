package com.service.employee.configuration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class EmployeeException extends RuntimeException {

    private String message;
}
