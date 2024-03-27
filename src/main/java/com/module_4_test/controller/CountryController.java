package com.module_4_test.controller;

import com.module_4_test.model.Country;
import com.module_4_test.service.impl.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/countries")
public class CountryController {
    @Autowired
    private CountryService countryService;
    @GetMapping("")
    public ResponseEntity<Iterable<Country>> getAllCountries() {
        List<Country> countries = (List<Country>) countryService.findAll();
        if(countries.isEmpty()){
            return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(countryService.findAll(),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Country> createCountry(@RequestBody Country country){
        return new ResponseEntity<>(countryService.save(country), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable Long id, @RequestBody Country country){
        Optional<Country> countryOptional = countryService.findById(id);
        if (countryOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        countryService.save(country);
        return new ResponseEntity<>(country, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Country> deleteTab1(@PathVariable Long id){
        Optional<Country> countryOptional = countryService.findById(id);
        if (countryOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        countryService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
