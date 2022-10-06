package com.service.employee.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.employee.configuration.EmployeeException;
import com.service.employee.dto.request.EmployeeRequest;
import com.service.employee.dto.response.EmployeeResponse;
import com.service.employee.dto.response.ResponseBody;
import com.service.employee.model.Employee;
import com.service.employee.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EmployeeService {

    final EmployeeRepository employeeRepository;

    public ResponseBody<List<Employee>> getAllEmployees() {
        // find all employee
        final List<Employee> employees = employeeRepository.findAll();
        var response = new ResponseBody<List<Employee>>();
        response.setSuccessResponse(employees);
        return response;
    }

    public ResponseBody<String> insertEmployee(EmployeeRequest employee) {
        try {
            if (employee.getId() == null) {
                employee.setId(UUID.randomUUID().toString());
            }
            var objectConverted = new ObjectMapper().convertValue(employee, Employee.class);
            employeeRepository.save(objectConverted);
            var response = new ResponseBody<String>();
            response.setSuccessResponse(null);
            return response;
        } catch (Exception e) {
            throw new EmployeeException(e.getMessage());
        }
    }

    public ResponseBody<String> updateEmployee(EmployeeRequest request) {
        var employee = employeeRepository.findById(request.getId());
        if (employee.isEmpty()) {
            throw new EmployeeException("Invalid request");
        }
        return insertEmployee(request);
    }

    public ResponseBody<EmployeeResponse> getEmployeeById(String id) {
        var employee = employeeRepository.findById(id);

        if (employee.isPresent()) {
            var response = new ResponseBody<EmployeeResponse>();
            var objectConverted = new ObjectMapper().convertValue(employee.get(), EmployeeResponse.class);
            response.setSuccessResponse(objectConverted);
            return response;
        } else {
            throw new EmployeeException("Not Found User");
        }

    }

    public ResponseBody<String> deleteById(String id) {
        var employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            employeeRepository.delete(employee.get());
            var response = new ResponseBody<String>();
            response.setSuccessResponse(null);
            return response;
        }
        throw new EmployeeException("Not Found User");
    }
}
