package com.islamlucas.projetantigaspi.shop;

import com.islamlucas.projetantigaspi.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface ShopRepository extends JpaRepository<Shop, Long> {
    Shop findByManagersContaining(User user);
}
