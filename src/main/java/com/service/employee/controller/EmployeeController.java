package com.service.employee.controller;

import com.service.employee.dto.request.EmployeeRequest;
import com.service.employee.dto.response.EmployeeResponse;
import com.service.employee.dto.response.ResponseBody;
import com.service.employee.model.Employee;
import com.service.employee.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("employee")
public class EmployeeController {

    final EmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<ResponseBody<List<Employee>>> getAllEmployee() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseBody<EmployeeResponse>> getEmployeeById(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @PostMapping("/insert")
    public ResponseEntity<ResponseBody<String>> insertEmployee(@RequestBody EmployeeRequest employee) {
        return ResponseEntity.ok(employeeService.insertEmployee(employee));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseBody<String>> updateEmployee(@RequestBody EmployeeRequest employee) {
        return ResponseEntity.ok(employeeService.updateEmployee(employee));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<ResponseBody<String>> deleteEmployee(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(employeeService.deleteById(id));
    }

}
