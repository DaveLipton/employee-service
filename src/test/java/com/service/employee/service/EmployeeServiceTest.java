package com.service.employee.service;

import com.service.employee.configuration.EmployeeException;
import com.service.employee.dto.request.EmployeeRequest;
import com.service.employee.model.Employee;
import com.service.employee.repository.EmployeeRepository;
import com.service.employee.services.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmployeeServiceTest {


    @InjectMocks
    EmployeeService employeeService;

    @Mock
    EmployeeRepository employeeRepository;

    @Test
    void getAllEmployeeTest() {
        var response = employeeService.getAllEmployees();
        Assertions.assertEquals("Success", response.getResultMsg());
    }

    @Test
    void getEmployeeByIdTest() {
        when(employeeRepository.findById(anyString())).thenReturn(Optional.of(Employee.builder().id("mock").build()));
        var response = employeeService.getEmployeeById("mock");
        Assertions.assertEquals("Success", response.getResultMsg());
    }

    @Test
    void getEmployeeByIdFailTest() {

        var response = Assertions.assertThrowsExactly(EmployeeException.class, () ->
                employeeService.getEmployeeById("mock"));
        Assertions.assertEquals("Not Found User", response.getMessage());
    }

    @Test
    void insertEmployeeTest() {
        var response = employeeService.insertEmployee(EmployeeRequest.builder().build());
        Assertions.assertEquals("Success", response.getResultMsg());
    }

    @Test
    void insertEmployeeFailTest() {
        when(employeeRepository.save(any())).thenThrow(NullPointerException.class);
        var response = Assertions.assertThrowsExactly(EmployeeException.class, () ->
                employeeService.insertEmployee(EmployeeRequest.builder().build()));
        Assertions.assertEquals(null, response.getMessage());
    }

    @Test
    void updateEmployeeTest() {
        when(employeeRepository.findById(anyString())).thenReturn(Optional.of(Employee.builder().id("mock").build()));
        var response = employeeService.updateEmployee(EmployeeRequest.builder()
                .id("mock").build());
        Assertions.assertEquals("Success", response.getResultMsg());
    }

    @Test
    void updateEmployeeFailTest() {

        var response = Assertions.assertThrowsExactly(EmployeeException.class, () -> employeeService.updateEmployee(EmployeeRequest.builder()
                .id("mock").build()));
        Assertions.assertEquals("Invalid request", response.getMessage());
    }

    @Test
    void deleteEmployeeTest() {
        when(employeeRepository.findById(anyString())).thenReturn(Optional.of(Employee.builder().id("mock").build()));
        var response = employeeService.deleteById("mock");
        Assertions.assertEquals("Success", response.getResultMsg());
    }

    @Test
    void deleteEmployeeFailTest() {

        var response = Assertions.assertThrowsExactly(EmployeeException.class, () -> employeeService.deleteById("mock"));
        Assertions.assertEquals("Not Found User", response.getMessage());
    }
}
