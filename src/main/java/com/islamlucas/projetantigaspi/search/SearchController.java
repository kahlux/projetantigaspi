package com.islamlucas.projetantigaspi.search;

import com.islamlucas.projetantigaspi.registration.RegistrationValidator;
import com.islamlucas.projetantigaspi.registration.ShopValidator;
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
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
public class SearchController {
    private final UserService userService;
    private final SecurityService securityService;
    private final RegistrationValidator registrationValidator;
    private final CategoryRepository categoryRepository;
    private final ShopValidator shopValidator;


    //search form traitement
    //fin du traitement search form
    @GetMapping("/search")
    public String search(@RequestParam("q") String txt, HttpServletRequest request) {
        //trouver toutes les entités liées au texte saisi


        return "search";
    }


}