package com.cognizant.orm_learn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cognizant.orm_learn.service.EmployeeService;
import java.util.List;
import com.cognizant.orm_learn.model.Employee;
@RestController
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

   @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
    LOGGER.info("START");
    List<Employee> employees = employeeService.getAllEmployees();
    LOGGER.info("END");
    return employees;
}
}