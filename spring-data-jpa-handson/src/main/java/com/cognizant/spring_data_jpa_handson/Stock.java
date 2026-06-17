package com.cognizant.spring_data_jpa_handson;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "stock")
public class Stock {

    @Id // Ee annotation fields kante pai untundi
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "st_id")
    private int id;

    @Column(name = "st_code")
    private String code;

    @Column(name = "st_date")
    private Date date;

    @Column(name = "st_open")
    private double open;

    @Column(name = "st_close")
    private double close;

    @Column(name = "st_volume")
    private long volume;

    //@Override
    public String toString() {
        return "Stock [id=" + id + ", code=" + code + ", open=" + open + ", close=" + close + "]";
    }
}