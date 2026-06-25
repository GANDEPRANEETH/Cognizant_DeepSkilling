package com.cognizant.orm_learn.model;
//import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "options")
public class Options {
    @Id
    @Column(name = "op_id")
    private int id;
    @Column(name = "op_text")
    private String text;
    @Column(name = "op_correct")
    private boolean correct;
    @ManyToOne
    @JoinColumn(name = "op_qt_id")
    private Question question;
   public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public boolean isCorrect() { return correct; }
    public void setCorrect(boolean correct) { this.correct = correct; }
    public Question getQuestion() { return question; }
public void setQuestion(Question question) { this.question = question; }
}