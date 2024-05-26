package com.example.dentistry.controllers;
import com.example.dentistry.dto.UserResponse;
import com.example.dentistry.entities.Patient;
import com.example.dentistry.entities.User;
import com.example.dentistry.enums.Role;
import com.example.dentistry.repositories.UserRepository;
import com.example.dentistry.services.AuthorizationService;
import com.example.dentistry.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class AuthorizationController {

    private UserRepository userRepository;
    private UserService userService;
    private final AuthorizationService authorizationService;

    //=========errors=========
    @GetMapping("/error")
    public String error(){
        return "error/error";
    }

    @GetMapping("error/accessDenied")
    private String accessDenied(){ return "/error/accessDenied";   }


    //=====Login=====
    @GetMapping("/")
    public String login(){ return "authorization/login";}

    @PostMapping("/login")
    public ModelAndView login(
            Model model,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession session) {

        User user = userRepository.findByUsername(username);
        Optional<UserResponse> userResponse = authorizationService.checkUser(username, password);
        session.setAttribute("user", userResponse.get());
        if (user != null && user.getPassword().equals(password)) {
            return switch (user.getRole()) {
                case ADMINISTRATOR -> new ModelAndView("redirect:/admin/home");
                case DOCTOR -> new ModelAndView("redirect:/doctor/home");
                case PATIENT -> new ModelAndView("redirect:/patient/home");
            };
        } else {
            return new ModelAndView("authorization/login", "message", "Неправильний логін або пароль");
        }
    }

    //=====Registration=========

    @GetMapping("/register")
    public String showRegistrationForm() { return "authorization/registration";}

    @PostMapping("/register")
    public ModelAndView register(
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("username") String username,
            @RequestParam("phone") String phone,
            @RequestParam("password") String password) {

        userService.createPatient(name, surname, username, password, phone, Role.PATIENT);
        return new ModelAndView("redirect:/register", "message", "Реєстрація успішна. Будь ласка, увійдіть в систему.");
    }

    //======Logout====
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
