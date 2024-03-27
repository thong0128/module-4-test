package com.module_4_test.controller;

import com.module_4_test.model.City;
import com.module_4_test.model.Country;
import com.module_4_test.service.impl.CityService;
import com.module_4_test.service.impl.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/cities")
public class CityController {
    @Autowired
    private CityService cityService;
    @Autowired
    private CountryService countryService;
    @ModelAttribute("countries")
    public Iterable<Country> countries() {
        return countryService.findAll();
    }
    @GetMapping("")
    public ModelAndView getAllCities() {
        ModelAndView modelAndView = new ModelAndView("city/index");
        Iterable<City> cities = cityService.findAll();
        modelAndView.addObject("cities", cities);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView createCityForm() {
        ModelAndView modelAndView = new ModelAndView("city/create");
        modelAndView.addObject("city", new City());
        return modelAndView;
    }
    @PostMapping("/create")
    public String createCity(@ModelAttribute("city") City city) {
        cityService.save(city);
        return "redirect:/cities";
    }
    @GetMapping("/update/{id}")
        public ModelAndView updateCity(@PathVariable Long id) {
        Optional<City> city = cityService.findById(id);
        if (city.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("city/update");
            modelAndView.addObject("city", city.get());
            return modelAndView;
        } else {
            return getAllCities();
        }
    }
    @PostMapping("/update/{id}")
    public String updateCity(@ModelAttribute("city") City city,
                         RedirectAttributes redirect) {
        cityService.save(city);
        redirect.addFlashAttribute("message", "Update City successfully");
        return "redirect:/cities";
    }
    @GetMapping("/delete/{id}")
    public ModelAndView deleteCity(@PathVariable Long id) {
        Optional<City> city = cityService.findById(id);
        if (city.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("city/delete");
            modelAndView.addObject("city", city.get());
            return modelAndView;
        } else {
            return getAllCities();
        }
    }
    @PostMapping("/delete/{id}")
    public String deleteCity(@PathVariable Long id,
                         RedirectAttributes redirect) {
        cityService.remove(id);
        redirect.addFlashAttribute("message", "Delete City successfully");
        return "redirect:/cities";
    }
    @GetMapping("/{id}")
    public ModelAndView getCityById(@PathVariable Long id) {
        Optional<City> city = cityService.findById(id);
        if (city.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("city/view");
            modelAndView.addObject("city", city.get());
            return modelAndView;
        }
        return getAllCities();
    }
}
