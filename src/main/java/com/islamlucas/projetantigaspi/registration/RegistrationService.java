package com.islamlucas.projetantigaspi.registration;

import com.islamlucas.projetantigaspi.shop.Shop;
import com.islamlucas.projetantigaspi.users.User;
import com.islamlucas.projetantigaspi.users.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    public void registerUser(User user)  {
        userService.signUpUser(user);
    }

    public void registerPro(User user, Shop shop) {
        userService.signUpPro(user, shop);
    }
}
