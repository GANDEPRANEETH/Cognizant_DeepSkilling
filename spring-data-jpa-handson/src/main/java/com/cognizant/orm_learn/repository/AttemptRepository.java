package com.cognizant.orm_learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.cognizant.orm_learn.model.Attempt;
import java.util.Set;

@Repository
public interface AttemptRepository extends JpaRepository<Attempt, Integer> {

    @Query("SELECT DISTINCT a FROM Attempt a " +
           "JOIN FETCH a.attemptQuestionList aq " +
           "JOIN FETCH aq.attemptOptionList ao " +
           "WHERE a.user.id = :userId")
    Set<Attempt> findAttemptDetailsByUserId(@Param("userId") int userId);
}