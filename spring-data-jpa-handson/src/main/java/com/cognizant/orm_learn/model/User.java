package com.cognizant.orm_learn.model;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "us_id")
    private int id;
    @Column(name = "us_name")
    private String name;
    @OneToMany(mappedBy = "user")
    private List<Attempt> attemptList;
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Attempt> getAttemptList() { return attemptList; }
    public void setAttemptList(List<Attempt> attemptList) { this.attemptList = attemptList; }
}