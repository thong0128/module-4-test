package com.module_4_test.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "countryId")
    private Country country;
    private Long population;
    private Long gdp;
    private String description;
    private Long area;
}
