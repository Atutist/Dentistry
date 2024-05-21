package com.example.dentistry.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class PatientDTO {
    private Integer id;
    private String name;
    private String surname;
}
