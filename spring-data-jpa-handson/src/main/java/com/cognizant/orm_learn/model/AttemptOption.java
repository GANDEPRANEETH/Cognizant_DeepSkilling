package com.cognizant.orm_learn.model;
import java.util.List;

import jakarta.persistence.*;
@Entity
@Table(name = "attempt_option")
public class AttemptOption {
    @Id
    @Column(name = "ao_id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "ao_aq_id")
    private AttemptQuestion attemptQuestion;
    @ManyToOne
    @JoinColumn(name = "ao_op_id")
    private Options option;
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public AttemptQuestion getAttemptQuestion() { return attemptQuestion; }
    public void setAttemptQuestion(AttemptQuestion attemptQuestion) { this.attemptQuestion = attemptQuestion; }
    public Options getOption() { return option; }
    public void setOption(Options option) { this.option = option; }
}