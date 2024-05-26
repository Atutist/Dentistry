package com.example.dentistry.services;

import com.example.dentistry.entities.Administrator;
import com.example.dentistry.entities.Doctor;
import com.example.dentistry.entities.Patient;
import com.example.dentistry.entities.User;
import com.example.dentistry.enums.Role;
import com.example.dentistry.repositories.AdministratorRepository;
import com.example.dentistry.repositories.DoctorRepository;
import com.example.dentistry.repositories.PatientRepository;
import com.example.dentistry.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AdministratorRepository administratorRepository;



    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }


    public void createPatient(String name, String surname, String username, String password, String phone, Role role) {
        Patient newPatient = new Patient();
        newPatient.setName(name);
        newPatient.setSurname(surname);
        newPatient.setUsername(username);
        newPatient.setPassword(password);
        newPatient.setPhone(phone);
        newPatient.setRole(role);
        patientRepository.save(newPatient);
    }

    public void createDoctor(String name, String surname, String username, String password, String specialization, Role role){
        Doctor newDoctor = new Doctor();
        newDoctor.setName(name);
        newDoctor.setSurname(surname);
        newDoctor.setUsername(username);
        newDoctor.setPassword(password);
        newDoctor.setSpecialization(specialization);
        newDoctor.setRole(role);
        doctorRepository.save(newDoctor);
    }

    public void createAdministrator(String name, String surname, String username, String password, String phone, Role role) {
        Administrator newAdministrator = new Administrator();
        newAdministrator.setName(name);
        newAdministrator.setSurname(surname);
        newAdministrator.setUsername(username);
        newAdministrator.setPassword(password);
        newAdministrator.setPhone(phone);
        newAdministrator.setRole(role);
        administratorRepository.save(newAdministrator);
    }

    public Patient findPatientById(Integer id) {
        return patientRepository.findById(id).orElse(null);
    }

    public void updatePatient(Patient patient) {
        patientRepository.save(patient);
    }

}
