package com.cognizant.spring_data_jpa_handson;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Country Not found")
public class CountryNotFoundException extends Exception {
    // You don't need any extra code here, the annotation handles it!
}