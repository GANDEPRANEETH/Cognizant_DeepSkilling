package com.cognizant.spring_data_jpa_handson;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.orm_learn.model.Employee;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    
    @Autowired
    @Qualifier("newEmployeeService")
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees() {
    return employeeService.getAllEmployees();
}
}