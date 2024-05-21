package com.example.dentistry.controllers;

import com.example.dentistry.dto.AppointmentDTO;
import com.example.dentistry.dto.PatientDTO;
import com.example.dentistry.dto.UserResponse;
import com.example.dentistry.entities.Appointment;
import com.example.dentistry.entities.Doctor;
import com.example.dentistry.entities.Patient;
import com.example.dentistry.enums.Role;
import com.example.dentistry.services.AppointmentService;
import com.example.dentistry.services.DoctorService;
import com.example.dentistry.services.PatientService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.AbstractMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class DoctorController {

    private final AppointmentService appointmentService;
    private final DoctorService doctorService;
    private final PatientService patientService;

    @GetMapping("/doctor/home")
    public String doctorHome(Model model, HttpSession session) {
        UserResponse currentUser = (UserResponse) session.getAttribute("user");
        if (currentUser.getRole() == Role.DOCTOR) {
            Doctor doctor = doctorService.findById(currentUser.getId());
            model.addAttribute("doctor", doctor);
            List<AppointmentDTO> confirmedAppointments = appointmentService.getConfirmedAppointmentsForDoctor(doctor.getId())
                    .stream()
                    .map(app -> new AppointmentDTO(app.getId(), app.getDateTime(), app.getPatient().getName(), app.getComments()))
                    .collect(Collectors.toList());
            List<AppointmentDTO> requestedAppointments = appointmentService.getRequestedAppointmentsForDoctor(doctor.getId())
                    .stream()
                    .map(app -> new AppointmentDTO(app.getId(), app.getDateTime(), app.getPatient().getName(), app.getComments()))
                    .collect(Collectors.toList());

            model.addAttribute("confirmedAppointments", confirmedAppointments);
            model.addAttribute("requestedAppointments", requestedAppointments);
            return "doctor/home";
        }
        return "redirect:/error/accessDenied";
    }


    @GetMapping("/doctor/manageAppointments")
    public String manageAppointment(Model model, HttpSession session) {
        UserResponse currentUser = (UserResponse) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/error/accessDenied";
        }
        if (currentUser.getRole() == Role.DOCTOR) {
            Doctor doctor = doctorService.findById(currentUser.getId());
            List<AppointmentDTO> requestedAppointments = appointmentService.getRequestedAppointmentsForDoctor(doctor.getId())
                    .stream()
                    .map(app -> new AppointmentDTO(app.getId(), app.getDateTime(), app.getPatient().getName(), app.getComments()))
                    .collect(Collectors.toList());

            model.addAttribute("appointments", requestedAppointments);
        } else {
            return "redirect:/error/accessDenied";
        }
        return "doctor/manageAppointments";
    }


    @GetMapping("/doctor/patients")
    public String patients(Model model, HttpSession session) {
        UserResponse currentUser = (UserResponse) session.getAttribute("user");
        if (currentUser.getRole() == Role.DOCTOR) {
            List<PatientDTO> patients = patientService.findAll();
            model.addAttribute("patients", patients);
            return "doctor/patients";
        }else {
            return "redirect:/error/accessDenied";
        }
    }
}
