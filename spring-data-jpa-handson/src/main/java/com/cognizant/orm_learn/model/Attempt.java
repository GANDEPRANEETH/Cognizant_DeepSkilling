package com.cognizant.orm_learn.model;
//import java.util.List;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;

import jakarta.persistence.*;
@Entity
@Table(name = "attempt")
public class Attempt {
    @Id
    @Column(name = "at_id")
    private int id;
    @Column(name = "at_date")
    private Date date;
    @ManyToOne
    @JoinColumn(name = "at_us_id")
    private User user;
    @OneToMany(mappedBy = "attempt")
    private Set<AttemptQuestion> attemptQuestionList = new HashSet<>();
    public Set<AttemptQuestion> getAttemptQuestionList() {
    return attemptQuestionList;
}

public void setAttemptQuestionList(Set<AttemptQuestion> attemptQuestionList) {
    this.attemptQuestionList = attemptQuestionList;
}

    public void setDate(Date date) {
    this.date = date;
    }
    public void setId(int id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }
    
}