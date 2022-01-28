package com.netcracker.application.service.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Product")
public class Product implements MappableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private BigInteger id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "amount_in_shop", nullable = false)
    private Integer amountInShop;
    @Column(name = "description")
    private String description;
    @Column(name = "price", nullable = false)
    private Double price;
    @Column(name = "discount")
    private Double discount;
    @Column(name = "maker_id", nullable = false)
    private BigInteger makerId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Product_and_Category",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private Set<Category> categories;
}
