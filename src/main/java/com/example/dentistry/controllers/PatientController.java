package com.example.dentistry.controllers;

import com.example.dentistry.dto.AppointmentDTO;
import com.example.dentistry.dto.UserResponse;
import com.example.dentistry.entities.Appointment;
import com.example.dentistry.entities.Doctor;
import com.example.dentistry.entities.Patient;
import com.example.dentistry.entities.User;
import com.example.dentistry.enums.Role;
import com.example.dentistry.services.AppointmentService;
import com.example.dentistry.services.DoctorService;
import com.example.dentistry.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class PatientController {

    private final DoctorService doctorService;
    private final AppointmentService appointmentService;
    private final UserService userService;


    @GetMapping("/available-times")
    public List<String> getAvailableTimes(@RequestParam("date") String dateString) {
        LocalDate date = LocalDate.parse(dateString);
        List<LocalTime> availableTimes = appointmentService.getAvailableTimes(date);
        return availableTimes.stream()
                .map(LocalTime::toString)
                .collect(Collectors.toList());
    }


    @GetMapping("/patient/home")
    public String patientHome(Model model, HttpSession session) {
        UserResponse currentUser = (UserResponse) session.getAttribute("user");
        if (currentUser != null && currentUser.getRole() == Role.PATIENT) {
            // Fetching confirmed appointments
            List<AppointmentDTO> confirmedAppointments = appointmentService.getConfirmedAppointmentsForPatient(currentUser.getId());
            model.addAttribute("confirmedAppointments", confirmedAppointments);

            // Fetching requested appointments
            List<AppointmentDTO> requestedAppointments = appointmentService.getRequestedAppointmentsForPatient(currentUser.getId());
            model.addAttribute("requestedAppointments", requestedAppointments);

            return "patient/home";
        } else {
            return "redirect:/error/accessDenied";
        }
    }


    @GetMapping("/patient/bookAppointment")
    public String manageAppointment(Model model,
                                    HttpSession session){
        UserResponse currentUser = (UserResponse) session.getAttribute("user");
        if (currentUser != null && currentUser.getRole() == Role.PATIENT) {
            Integer patientId = currentUser.getId();
            model.addAttribute("patientId", patientId);
            List<Doctor> doctors = doctorService.getAllDoctors();
            model.addAttribute("doctors", doctors);
            return "patient/bookAppointment";
        } else {
            return "redirect:/error/accessDenied";
        }
    }

    @GetMapping("/patient/appointments")
    public String viewAppointments(Model model, HttpSession session) {
        UserResponse currentUser = (UserResponse) session.getAttribute("user");
        if (currentUser != null && currentUser.getRole() == Role.PATIENT) {
            List<AppointmentDTO> appointments = appointmentService.getAllAppointmentsForPatient(currentUser.getId());
            model.addAttribute("appointments", appointments);
            return "patient/appointments";
        } else {
            return "redirect:/error/accessDenied";
        }
    }

    @GetMapping("/patient/personalAccount")
    public String personalAccount(Model model, HttpSession session) {
        UserResponse currentUser = (UserResponse) session.getAttribute("user");
        if (currentUser != null && currentUser.getRole() == Role.PATIENT) {
            Patient patient = userService.findPatientById(currentUser.getId());
            model.addAttribute("user", patient);
            return "/patient/personalAccount";
        } else {
            return "redirect:/error/accessDenied";
        }
    }

    @PostMapping("/updatePersonalInfo")
    public String updatePersonalInfo(@ModelAttribute Patient patient,
                                     HttpSession session,
                                     RedirectAttributes redirectAttributes) {
        UserResponse currentUser = (UserResponse) session.getAttribute("user");
        if (currentUser != null) {
            Patient existingPatient = userService.findPatientById(currentUser.getId());
            existingPatient.setName(patient.getName());
            existingPatient.setSurname(patient.getSurname());
            existingPatient.setPhone(patient.getPhone());

            userService.updatePatient(existingPatient);
            redirectAttributes.addFlashAttribute("successMessage", "You renewed your data");
            return "redirect:/patient/personalAccount";
        } else {
            return "redirect:/error/accessDenied";
        }
    }



}
