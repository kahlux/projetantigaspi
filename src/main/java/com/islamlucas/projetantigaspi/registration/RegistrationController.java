package com.islamlucas.projetantigaspi.registration;

import com.islamlucas.projetantigaspi.security.SecurityService;
import com.islamlucas.projetantigaspi.users.User;
import lombok.AllArgsConstructor;
import org.apache.catalina.realm.DataSourceRealm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@AllArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;
    private final SecurityService securityService;
    private final RegistrationValidator registrationValidator;

    @GetMapping("/registration")
    public String registration(Model model) {
        if (securityService.isAuthenticated()) {
            return "redirect:/home";
        }

        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        registrationValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        registrationService.register(userForm);

        securityService.autoLogin(userForm.getEmail(), userForm.getPassword());

        return "redirect:/home";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (securityService.isAuthenticated()) {
            return "redirect:/home";
        }

        if (error != null)
            model.addAttribute("error", "Mot de passe ou email incorrect");

        if (logout != null)
            model.addAttribute("message", "Vous avez été déconnecté");

        return "login";
    }
}