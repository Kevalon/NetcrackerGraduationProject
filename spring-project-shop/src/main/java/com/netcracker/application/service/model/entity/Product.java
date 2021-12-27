package com.netcracker.application.service.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.math.BigInteger;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    @Id
    @Column("id")
    private BigInteger id;
    @Column("name")
    private String name;
    @Column("maker_id")
    private BigInteger makerId;
    @Column("amount_in_shop")
    private Integer amountInShop;
    @Column("description")
    private String description;
    @Column("price")
    private Double price;
    @Column("discount")
    private Double discount;
}
