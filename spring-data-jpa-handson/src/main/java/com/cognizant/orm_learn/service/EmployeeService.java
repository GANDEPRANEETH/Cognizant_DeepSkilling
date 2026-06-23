package com.cognizant.orm_learn.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cognizant.orm_learn.model.Employee;
import com.cognizant.orm_learn.repository.EmployeeRepository;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        return employeeRepository.findById(id).orElse(null);
        
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
    @Transactional
    public double getAverageSalary(int id) {
    return employeeRepository.getAverageSalary(id);
    }
    @Transactional
    public List<Employee> getAllEmployeesNative() {
    return employeeRepository.getAllEmployeesNative();

    }
    @PersistenceContext
    private EntityManager entityManager;

    // 2. Ikkada ee method undali
    public List<Employee> getEmployeesByCriteria(String name, Double minSalary) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
        Root<Employee> employee = query.from(Employee.class);
        
        List<Predicate> predicates = new ArrayList<>();
        
        if (name != null) {
            predicates.add(cb.equal(employee.get("name"), name));
        }
        if (minSalary != null) {
            predicates.add(cb.greaterThan(employee.get("salary"), minSalary));
        }
        
        query.where(predicates.toArray(new Predicate[0]));
        
        return entityManager.createQuery(query).getResultList();
    }
    @Transactional
        public Page<Employee> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

}