package com.islamlucas.projetantigaspi.shop;

import com.islamlucas.projetantigaspi.security.SecurityService;
import com.islamlucas.projetantigaspi.users.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class ShopController {

    private final ShopService shopService;
    private final ShopRepository shopRepository;
    private final SecurityService securityService;
    private final CartRepository cartRepository;

    @GetMapping("/shops")
    public String accueilShops(Model model) {
        Long userLoggedId = securityService.getUserLogged().getId();
        List<Shop> lshop = shopRepository.findAll();
        model.addAttribute("shops",lshop);
        return "shops";
    }

    @GetMapping("/shops/{id}")
    public String shopPage(@PathVariable Long id, Model model) {
        model.addAttribute("carts", cartRepository.findAllByShopId(id));
        shopRepository.findById(id).ifPresent(shop -> model.addAttribute("shop", shop));
        return "shops/shop-home";
    }

}
