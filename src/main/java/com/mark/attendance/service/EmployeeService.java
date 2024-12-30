package com.mark.attendance.service;

import com.mark.attendance.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    Employee registerEmployee(Employee employee);

    List<Employee> getAllEmployees();
}
