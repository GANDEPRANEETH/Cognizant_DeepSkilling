package com.cognizant.orm_learn.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
@Table(name = "country")
public class Country {
    private static final Logger LOGGER = LoggerFactory.getLogger(Country.class);
    @Id
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;

    // 1. Default Constructor with Debug Log
    public Country() {
        LOGGER.info("Inside Country Constructor");
    }

    // 2. Getters and Setters with Debug Logs
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        LOGGER.info("Setting Code: {}", code);
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        LOGGER.info("Setting Name: {}", name);
        this.name = name;
    }

    // 3. Generated toString() Method
    @Override
    public String toString() {
        return "Country [code=" + code + ", name=" + name + "]";
    }
}