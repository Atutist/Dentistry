package com.example.dentistry.services;


import com.example.dentistry.dto.AppointmentDTO;
import com.example.dentistry.entities.Appointment;
import com.example.dentistry.entities.Doctor;
import com.example.dentistry.entities.Patient;
import com.example.dentistry.repositories.AppointmentRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class AppointmentService {
    private AppointmentRepository appointmentRepository;

    public List<Appointment> getConfirmedAppointmentsForDoctor(Integer doctorId) {
        return appointmentRepository.findAppointmentsByDoctorIdAndStatus(doctorId, "Confirmed");
    }

    public List<AppointmentDTO> getAppointmentsForPatient(Integer patientId) {
        return appointmentRepository.findByPatientId(patientId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private AppointmentDTO convertToDto(Appointment appointment) {
        return new AppointmentDTO(
                appointment.getId(), // Include the ID of the appointment
                appointment.getDateTime(),
                appointment.getDoctor().getName(),
                appointment.getComments()
        );
    }

    public List<Appointment> getRequestedAppointmentsForDoctor(Integer doctorId) {
        return appointmentRepository.findAppointmentsByDoctorIdAndStatus(doctorId, "Requested");
    }

    public List<AppointmentDTO> getAllAppointmentsForPatient(Integer patientId) {
        List<Appointment> appointments = new ArrayList<>();
        appointments.addAll(appointmentRepository.findAppointmentsByPatientIdAndStatus(patientId, "Requested"));
        appointments.addAll(appointmentRepository.findAppointmentsByPatientIdAndStatus(patientId, "Confirmed"));
        return appointments.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }


    public List<Appointment> getAppointmentsForDoctor(Integer doctorId) {
        return appointmentRepository.findAllByDoctorId(doctorId);
    }

    public List<LocalTime> getAvailableTimes(LocalDate date) {
        List<LocalTime> allTimes = List.of(
                LocalTime.of(9, 0), LocalTime.of(9, 40), /*...*/ LocalTime.of(14, 20)
        );
        List<Appointment> appointments = Collections.singletonList(appointmentRepository.findByDateTime(date));

        List<LocalTime> bookedTimes = appointments.stream()
                .map(Appointment::getDateTime)
                .map(LocalDateTime::toLocalTime)
                .toList();

        return allTimes.stream()
                .filter(time -> !bookedTimes.contains(time))
                .collect(Collectors.toList());
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public void requestAppointment(Patient patient, Doctor doctor, LocalDateTime dateTime, String comments) {
        // Check if the time slot is already booked for the selected doctor
        List<Appointment> existingAppointments = appointmentRepository.findByDoctorAndDateTime(doctor, dateTime);
        if (!existingAppointments.isEmpty()) {
            throw new IllegalStateException("This time slot is already booked.");
        }

        // Create a new appointment
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setDateTime(dateTime);
        appointment.setStatus("Requested");
        appointment.setComments(comments);
        appointmentRepository.save(appointment);
    }

    public Appointment confirmAppointment(Integer appointmentId, String doctorComments) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        appointment.setStatus("Confirmed");
        appointment.setComments(doctorComments);
        return appointmentRepository.save(appointment);
    }

    public List<AppointmentDTO> getConfirmedAppointmentsForPatient(Integer patientId) {
        return appointmentRepository.findAppointmentsByPatientIdAndStatus(patientId, "Confirmed")
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<AppointmentDTO> getRequestedAppointmentsForPatient(Integer patientId) {
        return appointmentRepository.findAppointmentsByPatientIdAndStatus(patientId, "Requested")
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public void cancelAppointment(Integer appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        appointment.setStatus("Cancelled");
        appointmentRepository.save(appointment);
    }

    public void updateAppointmentStatus(Long appointmentId, String newStatus) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found: " + appointmentId));
        appointment.setStatus(newStatus);
        appointmentRepository.save(appointment);
    }

}
