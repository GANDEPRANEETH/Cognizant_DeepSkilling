package com.cognizant.spring_data_jpa_handson;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
    List<Stock> findByCode(String code);
    List<Stock> findByOpenGreaterThan(double price);
}