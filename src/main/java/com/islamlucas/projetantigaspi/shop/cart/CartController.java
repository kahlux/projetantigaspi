package com.islamlucas.projetantigaspi.shop.cart;

import com.islamlucas.projetantigaspi.shop.CartRepository;
import com.islamlucas.projetantigaspi.shop.CategoryRepository;
import com.islamlucas.projetantigaspi.shop.ShopRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class CartController {

    private final CartRepository cartRepository;
    private final ShopRepository shopRepository;
    private final CategoryRepository categoryRepository;


    @GetMapping("/carts")
    public String cartsHomepage(Model model) {
        model.addAttribute("carts", cartRepository.findAll());
        model.addAttribute("shops", shopRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());

        return "carts/cart-home";
    }

    @GetMapping("/carts/category/{id}")
    public String cartsByCategory(@PathVariable Long id, Model model) {
        model.addAttribute("carts", cartRepository.findAllByShop_Category_Id(id));
        model.addAttribute("shops", shopRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());

        return "carts/cart-home";
    }


}
