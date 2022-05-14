package com.islamlucas.projetantigaspi.users;

import com.islamlucas.projetantigaspi.security.PasswordEncoder;
import com.islamlucas.projetantigaspi.shop.Shop;
import com.islamlucas.projetantigaspi.shop.ShopRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.expression.Lists;
import org.thymeleaf.spring5.ISpringTemplateEngine;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ShopRepository shopRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Mot de passe ou identifiant incorrect !"));
    }

    public void signUpUser(User user) {
        //sauvegarde de l'utilisateur avec un mot de passe crypté
        String encodedPassword = passwordEncoder.bCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(UserRole.USER);
        userRepository.save(user);
    }

    public void signUpPro(User user, Shop shop) {
        //sauvegarde de l'utilisateur avec mdp crypté PLUS enregistrement d'un shop associé au pro
        shop.setManagers(Collections.singletonList(user));
        Shop savedShop = shopRepository.save(shop);

        String encodedPassword = passwordEncoder.bCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(UserRole.SELLER);
        user.setShop(savedShop);
        userRepository.save(user);
    }
}
