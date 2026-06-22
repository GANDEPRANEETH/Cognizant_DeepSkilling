package com.cognizant.orm_learn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cognizant.orm_learn.service.AttemptService;
import com.cognizant.orm_learn.service.DepartmentService;
import com.cognizant.orm_learn.service.EmployeeService;
import com.cognizant.orm_learn.service.SkillService;
import com.cognizant.orm_learn.model.Attempt;
import com.cognizant.orm_learn.model.Department;
import com.cognizant.orm_learn.model.Employee;
import com.cognizant.orm_learn.model.Skill;
import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = {"com.cognizant.orm_learn"})
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);
    private static DepartmentService departmentService;
    private static EmployeeService employeeService;
    private static SkillService skillService;
    private static AttemptService attemptService;
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
        String[] beanNames = context.getBeanDefinitionNames();
        for (String beanName : beanNames) {
        if (beanName.contains("attemptService")) {
            System.out.println("Found bean: " + beanName);
        }
    }
        departmentService = context.getBean(DepartmentService.class);
        employeeService = context.getBean(EmployeeService.class);
        skillService = context.getBean(SkillService.class);
        attemptService = context.getBean(AttemptService.class);
        OrmLearnApplication app = context.getBean(OrmLearnApplication.class);
        
        testGetDepartment();
        testGetEmployee();
        testAddSkillToEmployee();
        testGetEmployee(); 
        testGetAllPermanentEmployees();
        app.testGetAttemptDetails();
        testGetAverageSalary();
        testGetAllEmployeesNative();
        testGetEmployeesByCriteria();
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

    public static void testGetAllPermanentEmployees() {
    LOGGER.info("Start"); 
 
    LOGGER.info("End");  
    }
    public void testGetAttemptDetails() {
    LOGGER.info("Fetching attempt details for User ID: 1");

    java.util.Set<com.cognizant.orm_learn.model.Attempt> attempts = attemptService.getAttemptDetails(1);
    
        for (com.cognizant.orm_learn.model.Attempt attempt : attempts) {
        LOGGER.info("Attempt ID: {}, Date: {}", attempt.getId(), attempt.getDate());
        }
    }
    private static void testGetAverageSalary() {
    LOGGER.info("Start");
    double avgSalary = employeeService.getAverageSalary(1); 
    LOGGER.info("Average Salary: {}", avgSalary);
    LOGGER.info("End");
    }
    private static void testGetAllEmployeesNative() {
    LOGGER.info("Start");
    List<Employee> employees = employeeService.getAllEmployeesNative();
    employees.forEach(e -> LOGGER.info("Employee: {}", e.getName()));
    LOGGER.info("End");
    }
    private static void testGetEmployeesByCriteria() {
    LOGGER.info("Start");
    // Test: Search for employees named "John Doe" with a salary > 40000
    List<Employee> employees = employeeService.getEmployeesByCriteria("John Doe", 40000.0);
    
    employees.forEach(e -> LOGGER.info("Found: {} | Salary: {}", e.getName(), e.getSalary()));
    LOGGER.info("End");
}
    

}
