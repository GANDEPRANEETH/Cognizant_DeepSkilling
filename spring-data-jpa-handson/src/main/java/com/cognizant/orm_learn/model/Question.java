package com.cognizant.orm_learn.model;
import java.util.List;


import jakarta.persistence.*;

@Entity
@Table(name = "question")
public class Question {
    @Id
    @Column(name = "qt_id")
    private int id;
    @Column(name = "qt_text")
    private String text;
    @OneToMany(mappedBy = "question")
    private List<Options> optionsList;
   public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public List<Options> getOptionsList() { return optionsList; }
public void setOptionsList(List<Options> optionsList) { this.optionsList = optionsList; }
}