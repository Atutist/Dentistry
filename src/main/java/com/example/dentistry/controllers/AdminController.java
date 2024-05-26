package com.example.dentistry.controllers;

import com.example.dentistry.dto.AppointmentDTO;
import com.example.dentistry.dto.UserResponse;
import com.example.dentistry.entities.Doctor;
import com.example.dentistry.entities.Patient;
import com.example.dentistry.enums.Role;
import com.example.dentistry.services.AppointmentService;
import com.example.dentistry.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class AdminController {
    private final UserService userService;
    private final AppointmentService appointmentService;

    @GetMapping("/admin/home")
    public String doctorHome(Model model, HttpSession session) {
        UserResponse currentUser = (UserResponse) session.getAttribute("user");
        if (currentUser != null && currentUser.getRole() == Role.ADMINISTRATOR) {
            return "admin/home";
        }
        return "redirect:/error/accessDenied";
    }
    @GetMapping("/admin/registerNewUser")
    public String registerNewUser(Model model, HttpSession session) {
        UserResponse currentUser = (UserResponse) session.getAttribute("user");
        if (currentUser != null && currentUser.getRole() == Role.ADMINISTRATOR) {
            return "admin/registerNewUser";
        }
        return "redirect:/error/accessDenied";
    }

    @PostMapping("/admin/registerUser")
    public String registerUser(@RequestParam String name, @RequestParam String surname,
                               @RequestParam String username, @RequestParam String password,
                               @RequestParam(required = false) String specialization,
                               RedirectAttributes redirectAttributes,
                               HttpSession session) {
        UserResponse currentUser = (UserResponse) session.getAttribute("user");
        if (currentUser != null && currentUser.getRole() == Role.ADMINISTRATOR) {
            userService.createDoctor(name, surname, username, password, specialization, Role.DOCTOR);
            redirectAttributes.addFlashAttribute("successMessage", "New doctor has benn added!");
            return "redirect:/admin/registerNewUser";
            }else {
                return "redirect:/error/accessDenied";
            }
    }

    @GetMapping("/admin/appointments")
    public String adminAppointments(Model model, HttpSession session) {
        UserResponse currentUser = (UserResponse) session.getAttribute("user");
        if (currentUser != null && currentUser.getRole() == Role.ADMINISTRATOR) {
            model.addAttribute("appointments", appointmentService.getAllAppointments());
            return "admin/appointments";
        }
        return "redirect:/error/accessDenied";
    }

    @PostMapping("/admin/updateAppointmentStatus")
    public String updateAppointmentStatus(
            @RequestParam("id") Long appointmentId,
            @RequestParam("status") String status,
            RedirectAttributes redirectAttributes) {
        try {
            appointmentService.updateAppointmentStatus(appointmentId, status);
            redirectAttributes.addFlashAttribute("successMessage", "Appointment status updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to update status: " + e.getMessage());
        }
        return "redirect:/admin/appointments";
    }
}
