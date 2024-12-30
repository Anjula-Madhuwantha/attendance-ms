package com.mark.attendance.controller;

import com.mark.attendance.model.Employee;
import com.mark.attendance.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping(value = "/creates", headers = "X-Api-Version=v1")
    public ResponseEntity<String> registerEmployee(@RequestBody Employee employee) {

        try {
            Employee registerEmployee = employeeService.registerEmployee(employee);
            return ResponseEntity.ok("Employee registered successfully with ID: " + registerEmployee.getEmployeeId());
        }
        catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping(value = "/employees", headers = "X-Api-Version=v1")
    public List<Employee> getEmployees() {

        return employeeService.getAllEmployees();
    }
}
