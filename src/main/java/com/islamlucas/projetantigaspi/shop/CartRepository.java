package com.islamlucas.projetantigaspi.shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByShop(Shop shop);
    List<Cart> findAllByShopId(Long id);
    List<Cart> findAllByShop_Category_Id(Long catId);
}
