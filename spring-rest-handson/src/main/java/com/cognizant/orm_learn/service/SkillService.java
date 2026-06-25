package com.cognizant.orm_learn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cognizant.orm_learn.model.Skill;
import com.cognizant.orm_learn.repository.SkillRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class SkillService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SkillService.class);

    @Autowired
    private SkillRepository skillRepository;

    @Transactional
    public Skill get(int id) {
        LOGGER.info("Start");
        return skillRepository.findById(id).orElse(null); // Skill ni fetch chesthundi [cite: 151]
    }

    @Transactional
    public void save(Skill skill) {
        LOGGER.info("Start");
        skillRepository.save(skill); // Skill ni save chesthundi [cite: 151]
        LOGGER.info("End");
    }
}