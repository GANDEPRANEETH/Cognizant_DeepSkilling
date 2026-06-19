package com.cognizant.spring_data_jpa_handson;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.cognizant.orm_learn.model.Employee;
import com.cognizant.orm_learn.model.Skill;
import com.cognizant.orm_learn.service.DepartmentService;
import com.cognizant.orm_learn.service.EmployeeService;
import com.cognizant.orm_learn.service.SkillService;

@SpringBootApplication
@EntityScan("com.cognizant")
@EnableJpaRepositories("com.cognizant") 
@ComponentScan(basePackages = {"com.cognizant"})
public class SpringDataJpaHandsonApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringDataJpaHandsonApplication.class);

    @Autowired
    private CountryRepository countryRepository;
    
    

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

@   Autowired
    private SkillService skillService;
    @Autowired

    private StockRepository stockRepository;
    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaHandsonApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        testGetEmployee();
        LOGGER.info("Inside run method");      
        List<Country> countries = countryRepository.findAll();

        List<Stock> stocks = stockRepository.findAll();
        LOGGER.info("Stock Details: {}", stocks);

        List<Stock> fbStocks = stockRepository.findByCode("FB");
        LOGGER.info("Stocks with code FB: {}", fbStocks);
        List<Stock> expensiveStocks = stockRepository.findByOpenGreaterThan(1000.0);
        LOGGER.info("Stocks with open price > 1000: {}", expensiveStocks);

        
        LOGGER.info("Fetching all countries from DB:");
    
        for (Country country : countries) {
            LOGGER.info("Code: {}, Name: {}", country.getCode(), country.getName());
        }
        List<Country> ouCountries = countryRepository.findByNameContainingIgnoreCase("ou");
        System.out.println("Countries with 'ou': " + ouCountries);

     

        List<Country> zCountries = countryRepository.findByNameStartingWith("Z");
        
        zCountries.forEach(c -> System.out.println("Country starting with Z: " + c.getName()));

    }
       private void testGetEmployee() {
        LOGGER.info("Start");
        Employee employee = employeeService.get(1);
        
        if (employee != null) {
            LOGGER.info("Employee: {}", employee.getName());
            // NullPointerException ni avoid cheyadaniki department kuda check cheyi
            if (employee.getDepartment() != null) {
                LOGGER.info("Department: {}", employee.getDepartment().getName());
            } else {
                LOGGER.info("Department details levu bro!");
            }
        } else {
            LOGGER.info("Employee record ledu bro!");
        }
        
        Skill skill = skillService.get(1);
        if (skill != null) {
            LOGGER.info("Skill details: {}", skill);
        } else {
            LOGGER.info("Skill record ledu bro!");
        }
    }

}