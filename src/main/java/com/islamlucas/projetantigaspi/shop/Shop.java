package com.islamlucas.projetantigaspi.shop;

import com.islamlucas.projetantigaspi.users.User;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

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
@Table(name = "shop", schema = "public")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "category_id")
    @OneToOne
    private Category category;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private List<Cart> carts = new ArrayList<>();

    @OneToMany(mappedBy = "shop")
    private List<User> managers = new ArrayList<>();
}
