package com.islamlucas.projetantigaspi.users.pro;

import com.islamlucas.projetantigaspi.security.SecurityService;
import com.islamlucas.projetantigaspi.shop.Cart;
import com.islamlucas.projetantigaspi.shop.CartRepository;
import com.islamlucas.projetantigaspi.shop.ShopRepository;
import com.islamlucas.projetantigaspi.shop.ShopService;
import com.islamlucas.projetantigaspi.users.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class ProController {

    private final ShopService shopService;
    private final ShopRepository shopRepository;
    private final SecurityService securityService;
    private final CartRepository cartRepository;

    @GetMapping("/pro/home")
    public String accueilPro(Model model) {
        User userLogged = securityService.getUserLogged();
        List<Cart> carts = cartRepository.findAllByShop(userLogged.getShop());
        System.out.println(carts.size());
        model.addAttribute("cartForm", new Cart());
        model.addAttribute("carts", carts);

        return "pro/pro-panel";
    }

    @PostMapping("/pro/addCart")
    public String proAddCart(@ModelAttribute Cart cart) {
        User userLogged = securityService.getUserLogged();
        cart.setShop(userLogged.getShop());
        cartRepository.save(cart);
        return "redirect:/pro/home";
    }

    @GetMapping("/pro/deleteCart/{cartId}")
    public String proDeleteCart(@PathVariable Long cartId) {
        cartRepository.deleteById(cartId);
        return "redirect:/pro/home";
    }
}
