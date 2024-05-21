package com.example.dentistry.services;

import com.example.dentistry.dto.PatientDTO;
import com.example.dentistry.entities.Patient;
import com.example.dentistry.repositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;

    public List<PatientDTO> findAll() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream()
                .map(patient -> new PatientDTO(patient.getId(), patient.getName(), patient.getSurname()))
                .collect(Collectors.toList());
    }
}
