package com.islamlucas.projetantigaspi.search;

import com.islamlucas.projetantigaspi.registration.RegistrationValidator;
import com.islamlucas.projetantigaspi.registration.ShopValidator;
import com.islamlucas.projetantigaspi.security.SecurityService;
import com.islamlucas.projetantigaspi.shop.*;
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
import com.islamlucas.projetantigaspi.users.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class SearchController {
    private final UserService userService;
    private final SecurityService securityService;
    private final RegistrationValidator registrationValidator;
    private final CategoryRepository categoryRepository;
    private final ShopValidator shopValidator;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ShopRepository shopRepository;


    //search form traitement
    //fin du traitement search form
    @GetMapping("/search")
    public String search(@RequestParam("q") String q,Model model, HttpServletRequest request) {
        //trouver toutes les entités liées au texte saisi
        ArrayList<Object> result = new ArrayList<>();
        String user="";
        String shop="";
        String cart="";
        Optional<User> userr;
        Optional<Shop> shopp;
        Optional<Cart> cartt;
        userr = Optional.of(userRepository.findByEmailOrFirstNameOrLastName(q, q, q).orElse(new User()));
        shopp = Optional.of(shopRepository.findByNameOrCityOrAddress(q, q, q).orElse(new Shop()));
        cartt = Optional.of(cartRepository.findByLibelleOrDescription(q, q).orElse(new Cart()));
         user="email : "+userr.get().getEmail()+" firstname: "+userr.get().getFirstName()+" lastname: "+userr.get().getLastName();
         shop ="name: "+shopp.get().getName()+" city:"+shopp.get().getCity()+" address: "+shopp.get().getAddress();
         cart ="libelle: "+cartt.get().getLibelle()+" description: "+cartt.get().getDescription();

        result.add(user);
        result.add(shop);
        result.add(cart);

        model.addAttribute("result",result);

        return "search";
    }


}