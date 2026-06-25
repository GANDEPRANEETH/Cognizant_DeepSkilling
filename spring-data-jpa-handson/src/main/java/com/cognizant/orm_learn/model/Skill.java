package com.cognizant.orm_learn.model;

import jakarta.persistence.*;
import java.util.Set;
@Entity
@Table(name = "skill")
public class Skill {

    @Id
    @Column(name = "sk_id")
    private int id;

    @Column(name = "sk_name")
    private String name;

    public Skill() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "Skill [id=" + id + ", name=" + name + "]";
    }
    @ManyToMany(mappedBy = "skillList")
    private Set<Employee> employeeList;

    public Set<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(Set<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}