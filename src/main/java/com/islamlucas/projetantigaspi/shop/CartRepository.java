package com.islamlucas.projetantigaspi.shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByShop(Shop shop);

    List<Cart> findAllByShopId(Long id);

    Optional<Cart> findByLibelleOrDescription(String libelle,String description);
}
