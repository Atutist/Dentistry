package com.example.dentistry.controllers;


import com.example.dentistry.entities.Appointment;
import com.example.dentistry.entities.Doctor;
import com.example.dentistry.entities.Patient;
import com.example.dentistry.services.AppointmentService;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/appointments")
@AllArgsConstructor
public class AppointmentController {
    private AppointmentService appointmentService;


    @PostMapping("/requestAppointment")
    public String requestAppointment(@RequestParam("patientId") Patient patient,
                                     @RequestParam("doctorId") Doctor doctor,
                                     @RequestParam("date") String date,
                                     @RequestParam("time") String time,
                                     @RequestParam("comments") String comments,
                                     RedirectAttributes redirectAttributes) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate appointmentDate = LocalDate.parse(date, dateFormatter);
        LocalTime appointmentTime = LocalTime.parse(time);
        LocalDateTime dateTime = LocalDateTime.of(appointmentDate, appointmentTime);
        appointmentService.requestAppointment(patient, doctor, dateTime, comments);
        redirectAttributes.addFlashAttribute("successMessage", "Appointment is booked");
        return "redirect:/patient/bookAppointment"; // Перенаправлення на потрібну сторінку
    }

    @PostMapping("/confirmAppointment/{appointmentId}")
    public String confirmAppointment(@PathVariable Integer appointmentId,
                                     @RequestParam("comments") String doctorComment,
                                     RedirectAttributes redirectAttributes) {
        Appointment appointment = appointmentService.confirmAppointment(appointmentId, doctorComment);
        redirectAttributes.addFlashAttribute("successMessage", "Appointment confirmed successfully!");
        return "redirect:/doctor/manageAppointments";
    }

    @PostMapping("/cancel/{appointmentId}")
    public String cancelAppointment(@PathVariable Integer appointmentId, RedirectAttributes redirectAttributes) {
        try {
            appointmentService.cancelAppointment(appointmentId);
            redirectAttributes.addFlashAttribute("successMessage", "Appointment cancelled successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to cancel appointment.");
        }
        return "redirect:/patient/appointments";
    }

}
