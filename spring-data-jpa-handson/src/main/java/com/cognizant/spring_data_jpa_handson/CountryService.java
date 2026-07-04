package com.cognizant.spring_data_jpa_handson;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public List<Country> getAllCountries() {
        // This line fetches all countries directly from your repository
        return countryRepository.findAll();
    }
    public Country getCountry(String code) throws CountryNotFoundException {
        List<Country> countries = countryRepository.findAll();
        
        Country country = countries.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElse(null);

        if (country == null) {
            throw new CountryNotFoundException();
        }
        return country;
    }
}