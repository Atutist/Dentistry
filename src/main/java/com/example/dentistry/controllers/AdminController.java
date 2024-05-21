package com.example.dentistry.controllers;

import com.example.dentistry.dto.AppointmentDTO;
import com.example.dentistry.dto.UserResponse;
import com.example.dentistry.entities.Doctor;
import com.example.dentistry.entities.Patient;
import com.example.dentistry.enums.Role;
import com.example.dentistry.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AdminController {
    private UserService userService;

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

    @PostMapping("admin/registerUser")
    public String registerUser(@RequestParam String name, @RequestParam String surname,
                               @RequestParam String username, @RequestParam String password,
                               @RequestParam(required = false) String phone,
                               @RequestParam(required = false) String specialization,
                               @RequestParam Role role,
                               RedirectAttributes redirectAttributes,
                               HttpSession session) {
        UserResponse currentUser = (UserResponse) session.getAttribute("user");
        if (currentUser != null && currentUser.getRole() == Role.ADMINISTRATOR) {
        try {
            switch (role) {
                case PATIENT:
                    userService.createPatient(name, surname, username, password, phone, role);
                    break;
                case DOCTOR:
                    userService.createDoctor(name, surname, username, password, specialization, role);
                    break;
                case ADMINISTRATOR:
                    userService.createAdministrator(name, surname, username, password, phone, role);
                    break;
            }
            redirectAttributes.addFlashAttribute("successMessage", "User registered successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to register user.");
        }
            return "redirect:/admin/registerUser";
        }else {
            return "redirect:/error/accessDenied";
        }
    }
}
