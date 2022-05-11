package com.islamlucas.projetantigaspi.cart;

import com.islamlucas.projetantigaspi.shop.Shop;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
@ToString
@EqualsAndHashCode
@Table(name = "cart", schema = "public")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

    @Column(name = "nom")
    private String libelle;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private int price;
}