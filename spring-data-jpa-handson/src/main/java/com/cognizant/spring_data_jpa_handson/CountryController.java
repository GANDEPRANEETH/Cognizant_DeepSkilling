package com.cognizant.spring_data_jpa_handson;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/countries") // Sets the base path for all methods
public class CountryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    @Autowired
    private CountryService countryService;

    // Maps to GET /countries
    @GetMapping
    public List<Country> getAllCountries() {
        return countryService.getAllCountries();
    }

    // Maps to GET /countries/{code}

    @GetMapping("/{code}")
    public Country getCountry(@PathVariable String code) throws CountryNotFoundException {
    Country country = countryService.getCountry(code);
    if (country == null) {
        throw new CountryNotFoundException();
    }
    return country;
}
    

    // Maps to GET /countries/india
    @GetMapping("/india") 
    public Country getCountryIndia() throws CountryNotFoundException {
        return countryService.getCountry("in");
    }
    
}