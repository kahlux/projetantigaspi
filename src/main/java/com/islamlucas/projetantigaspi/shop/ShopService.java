package com.islamlucas.projetantigaspi.shop;

import com.islamlucas.projetantigaspi.users.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;

    public Shop findAllCartsByManagerId(User user) {
        return this.shopRepository.findByManagersContaining(user);
    }
}
