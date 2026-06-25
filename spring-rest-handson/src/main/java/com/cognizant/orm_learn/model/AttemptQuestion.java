package com.cognizant.orm_learn.model;
//import java.util.List;
import java.util.Set;
import java.util.HashSet;
import jakarta.persistence.*;

@Entity
@Table(name = "attempt_question")
public class AttemptQuestion {
    @Id
    @Column(name = "aq_id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "aq_at_id")
    private Attempt attempt;
    @ManyToOne
    @JoinColumn(name = "aq_qt_id")
    private Question question;
    @OneToMany(mappedBy = "attemptQuestion")
    private Set<AttemptOption> attemptOptionList = new HashSet<>();

    public Set<AttemptOption> getAttemptOptionList() {
    return attemptOptionList;
}

public void setAttemptOptionList(Set<AttemptOption> attemptOptionList) {
    this.attemptOptionList = attemptOptionList;
}
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Attempt getAttempt() { return attempt; }
    public void setAttempt(Attempt attempt) { this.attempt = attempt; }
    public Question getQuestion() { return question; }
    public void setQuestion(Question question) { this.question = question; }
    
}