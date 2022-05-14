package com.islamlucas.projetantigaspi.shop;

import com.islamlucas.projetantigaspi.security.SecurityService;
import com.islamlucas.projetantigaspi.users.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/pro/home")
    public String accueilPro(Model model) {
        Long userLoggedId = securityService.getUserLogged().getId();
        Shop shop = shopRepository.getById(userLoggedId);
        List<Cart> carts = cartRepository.findAllByShopId(shop.getId());

        model.addAttribute("carts", carts);

        return "pro/pro-panel";
    }
}
