package com.cognizant.spring_data_jpa_handson;

import java.util.ArrayList;
import java.util.List;
import jakarta.annotation.PostConstruct;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.cognizant.orm_learn.model.Department;

@Component
public class DepartmentDao {
    // Line 10: Ensure the type is clear
    private static List<Department> DEPARTMENT_LIST = new ArrayList<Department>();

    @PostConstruct
    @SuppressWarnings("unchecked")
    public void init() {
    // 1. Create the context pointing to your XML file
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("department.xml");
    
    // 2. Retrieve the list using the id defined in department.xml
    DEPARTMENT_LIST = (List<Department>) context.getBean("departmentList");
    
    // 3. Close the context to free resources
    context.close();
    }

    public List<Department> getAllDepartments() {
        return DEPARTMENT_LIST;
    }
}