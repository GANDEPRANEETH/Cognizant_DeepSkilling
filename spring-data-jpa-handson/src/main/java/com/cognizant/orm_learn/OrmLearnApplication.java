package com.cognizant.orm_learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cognizant.orm_learn.service.DepartmentService;
import com.cognizant.orm_learn.service.EmployeeService;
import com.cognizant.orm_learn.service.SkillService;
import com.cognizant.orm_learn.model.Department;
import com.cognizant.orm_learn.model.Employee;
import com.cognizant.orm_learn.model.Skill;

@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);
    private static DepartmentService departmentService;
    private static EmployeeService employeeService;
    private static SkillService skillService;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
        departmentService = context.getBean(DepartmentService.class);
        employeeService = context.getBean(EmployeeService.class);
        skillService = context.getBean(SkillService.class);
        
        testGetDepartment();
        testGetEmployee();
        testAddSkillToEmployee();
        testGetEmployee(); 
    }

    private static void testGetDepartment() {
        LOGGER.info("Start");
        Department department = departmentService.get(1);
        LOGGER.info("Department: {}", department.getName());
        LOGGER.info("Employees: {}", department.getEmployeeList());
        LOGGER.info("End");
    }
    private static void testGetEmployee() {
        LOGGER.info("Start");
        Employee employee = employeeService.get(1);
        LOGGER.info("Employee: {}", employee.getName());
        LOGGER.info("Skills: {}", employee.getSkillList());
        LOGGER.info("End"); 

    }
    private static void testAddSkillToEmployee() {
        LOGGER.info("Start");
        Employee employee = employeeService.get(1);
        Skill skill = skillService.get(3);
        employee.getSkillList().add(skill);
        employeeService.save(employee);
        LOGGER.info("Skill added successfully!");
        LOGGER.info("End");
    }
}