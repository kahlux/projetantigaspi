package com.islamlucas.projetantigaspi.shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface ShopRepository extends JpaRepository<Shop, Long> {

    List<Shop> findAll();
}