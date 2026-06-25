package com.cognizant.orm_learn.service;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cognizant.orm_learn.repository.AttemptRepository;
import com.cognizant.orm_learn.model.Attempt;



@Service
public class AttemptService {

    @Autowired
    private AttemptRepository attemptRepository;

    public Set<Attempt> getAttemptDetails(int userId) {
        return attemptRepository.findAttemptDetailsByUserId(userId);
    }
}