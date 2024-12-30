package com.mark.attendance.service.impl;

import com.mark.attendance.model.Employee;
import com.mark.attendance.repository.EmployeeRepository;
import com.mark.attendance.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee registerEmployee(Employee employee) {

        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {

        return employeeRepository.findAll();
    }

}
