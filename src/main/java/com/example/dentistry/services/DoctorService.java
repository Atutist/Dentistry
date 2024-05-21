package com.example.dentistry.services;


import com.example.dentistry.entities.Doctor;
import com.example.dentistry.entities.Patient;
import com.example.dentistry.repositories.DoctorRepository;
import com.example.dentistry.repositories.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class DoctorService {
    private final DoctorRepository doctorRepository;


    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor findById(Integer id) {
        return doctorRepository.findById(id).orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));
    }

}
