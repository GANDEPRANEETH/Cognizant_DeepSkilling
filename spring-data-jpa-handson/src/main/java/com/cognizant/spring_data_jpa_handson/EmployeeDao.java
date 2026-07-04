package com.cognizant.spring_data_jpa_handson;
import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Component;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.cognizant.orm_learn.model.Employee;

import jakarta.annotation.PostConstruct;

@Component
public class EmployeeDao {
    private static List<Employee> EMPLOYEE_LIST = new ArrayList<>();

    @PostConstruct
    @SuppressWarnings("unchecked")
    public void init() {
        // Load the context and read the bean defined in employee.xml
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("employee.xml");
        
        // Ensure "employeeList" matches the id in your employee.xml
        EMPLOYEE_LIST = (List<Employee>) context.getBean("employeeList");
        
        context.close();
    }

    public List<Employee> getAllEmployees() {
        return EMPLOYEE_LIST;
    }
}