package com.islamlucas.projetantigaspi.registration;

import com.islamlucas.projetantigaspi.security.SecurityService;
import com.islamlucas.projetantigaspi.shop.CategoryRepository;
import com.islamlucas.projetantigaspi.shop.Shop;
import com.islamlucas.projetantigaspi.users.User;
import com.islamlucas.projetantigaspi.shop.Category;
import com.islamlucas.projetantigaspi.users.UserRepository;
import com.islamlucas.projetantigaspi.shop.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@AllArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;
    private final SecurityService securityService;
    private final RegistrationValidator registrationValidator;

    private final CategoryRepository categoryRepository;

    @GetMapping("/sign-in/user")
    public String registrationUser(Model model) {
        if (securityService.isAuthenticated()) {
            return "redirect:/home";
        }

        model.addAttribute("userForm", new User());

        return "registration";
    }

    @GetMapping("/sign-in/pro")
    public String registrationPro(Model model) {
        if (securityService.isAuthenticated()) {
            return "redirect:/home";
        }

        model.addAttribute("userForm", new User());
        model.addAttribute("shopForm", new Shop());
        model.addAttribute("categories",categoryRepository.findAll());


        return "registration-pro";
    }

    @PostMapping("/sign-in/user")
    public String registerUser(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        registrationValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        registrationService.registerUser(userForm);

        securityService.autoLogin(userForm.getEmail(), userForm.getPassword());

        return "redirect:/home";
    }

    @PostMapping("/sign-in/pro")
    public String registerPro(@ModelAttribute("userForm") User userForm, @ModelAttribute("shopForm") Shop shopForm, BindingResult bindingResult) {
            System.out.println(shopForm);
            System.out.println(userForm);
            if(userForm.getId()!=null){
                registrationValidator.validate(userForm, bindingResult);
            }




        if (bindingResult.hasErrors()) {
            return "registration-pro";
        }

        registrationService.registerPro(userForm, shopForm);

        securityService.autoLogin(userForm.getEmail(), userForm.getPassword());

        return "redirect:/pro/home";
    }

    @GetMapping("/login")
    public String login() {
        if(securityService.isAuthenticated()) {
            return "redirect:/home";
        }
        return "login";
    }
}