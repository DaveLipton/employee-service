package com.service.employee.controller;

import com.service.employee.dto.request.EmployeeRequest;
import com.service.employee.services.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest
public class EmployeeControllerTest {

    @InjectMocks
    EmployeeController employeeController;

    @Mock
    EmployeeService employeeService;

    @Test
    void getAllEmployeeTest() {
        var response = employeeController.getAllEmployee();
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getEmployeeByIdTest() {
        var response = employeeController.getEmployeeById("mock");
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void insertEmployeeTest() {
        var response = employeeController.insertEmployee(EmployeeRequest.builder().build());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void updateEmployeeTest() {
        var response = employeeController.updateEmployee(EmployeeRequest.builder()
                .id("mock").build());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deleteEmployeeTest() {
        var response = employeeController.deleteEmployee("mock");
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
