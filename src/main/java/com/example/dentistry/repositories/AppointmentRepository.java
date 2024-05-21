package com.example.dentistry.repositories;

import com.example.dentistry.entities.Appointment;
import com.example.dentistry.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Optional<Appointment> findById(Integer id);

    List<Appointment> findByDoctorAndDateTime(Doctor doctor, LocalDateTime dateTime);

    List<Appointment> findAllByDoctorId(Integer doctorId);
    List<Appointment> findAppointmentsByDoctorIdAndStatus(Integer doctorId, String status);

    List<Appointment> findByPatientId(Integer id);

    List<Appointment> findAppointmentsByPatientIdAndStatus(Integer patientId, String status);
    Appointment findByDateTime(LocalDate date);
}
