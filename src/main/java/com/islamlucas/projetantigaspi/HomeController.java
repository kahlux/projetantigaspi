package com.islamlucas.projetantigaspi;

import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Controller
@RequestMapping("/home")
@AllArgsConstructor
public class HomeController {

    private final UserDetailsService userDetailsService;

    @GetMapping
    public String home() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().toArray()[0].toString();

        if (role.equals("ADMIN")) {
            return "redirect:/admin/home";
        } else if (role.equals("SELLER")){
            return "redirect:/pro/home";
        }

        return "home";
    }
}
