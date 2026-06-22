package com.cognizant.orm_learn.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cognizant.orm_learn.model.Employee;
import com.cognizant.orm_learn.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public Employee get(int id) {
        LOGGER.info("Start");
        return employeeRepository.findById(id).orElse(null); // Employee ni fetch chesthundi [cite: 157]
    }

    @Transactional
    public void save(Employee employee) {
        LOGGER.info("Start");
        employeeRepository.save(employee); // Employee ni save chesthundi [cite: 163]
        LOGGER.info("End");
    }
      @Transactional
    public List<Employee> getAllPermanentEmployees() {
    LOGGER.info("Start");
    return employeeRepository.getAllPermanentEmployees();
    }
    
}