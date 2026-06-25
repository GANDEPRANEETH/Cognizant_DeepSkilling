package com.cognizant.orm_learn.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.cognizant.orm_learn.model.Employee;
import com.cognizant.orm_learn.model.EmployeeProjection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

   @Query(value = "SELECT e FROM Employee e left join fetch e.department d left join fetch e.skillList WHERE e.permanent = true")
    List<Employee> getAllPermanentEmployees();
    @Query("SELECT AVG(e.salary) FROM Employee e WHERE e.department.id = :id")
    double getAverageSalary(@Param("id") int id);
    @Query(value = "SELECT * FROM employee", nativeQuery = true)
    List<Employee> getAllEmployeesNative();
    Page<Employee> findAll(Pageable pageable);
    List<EmployeeProjection> findAllProjectedBy();
    
}
