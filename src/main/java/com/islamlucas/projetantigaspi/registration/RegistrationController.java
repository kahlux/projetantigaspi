package com.islamlucas.projetantigaspi.registration;

import com.islamlucas.projetantigaspi.security.SecurityService;
import com.islamlucas.projetantigaspi.shop.CategoryRepository;
import com.islamlucas.projetantigaspi.shop.Shop;
import com.islamlucas.projetantigaspi.users.User;
import com.islamlucas.projetantigaspi.users.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
public class RegistrationController {
    private final UserService userService;
    private final SecurityService securityService;
    private final RegistrationValidator registrationValidator;
    private final CategoryRepository categoryRepository;
    private final ShopValidator shopValidator;

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
        model.addAttribute("categories", categoryRepository.findAll());

        return "registration-pro";
    }

    @PostMapping("/sign-in/user")
    public String registerUser(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, HttpServletRequest request) {
        registrationValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.signUpUser(userForm);

        securityService.autoLogin(userForm.getEmail(), userForm.getPassword(), request);

        return "redirect:/home";
    }

    @PostMapping("/sign-in/pro")
    public String registerPro(@ModelAttribute("userForm") User userForm,
                              BindingResult resultUser,
                              @ModelAttribute("shopForm") Shop shopForm,
                              BindingResult resultShop,
                              HttpServletRequest request) {
        registrationValidator.validate(userForm, resultUser);
        shopValidator.validate(shopForm, resultShop);

        if (resultUser.hasErrors() || resultShop.hasErrors()) {
            return "registration-pro";
        }

        userService.signUpPro(userForm, shopForm);

        securityService.autoLogin(userForm.getEmail(), userForm.getPassword(), request);

        return "redirect:/home";
    }

    @GetMapping("/login")
    public String login() {
        if(securityService.isAuthenticated()) {
            return "redirect:/home";
        }

        return "login";
    }
}