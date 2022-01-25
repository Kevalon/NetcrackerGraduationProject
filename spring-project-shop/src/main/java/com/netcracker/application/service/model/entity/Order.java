package com.netcracker.application.service.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Order")
public class Order implements MappableEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private BigInteger id;
    @Column(name = "user_id")
    private BigInteger userId;
    @Column(name = "total_sum")
    private Double totalSum;
    @Column(name = "amount_of_goods", nullable = false)
    private Integer goodsAmount;
    @Column(name = "is_closed", columnDefinition = "BIT")
    private boolean isClosed;
    @Column(name = "creation_date")
    private Timestamp creationDate;

    @ManyToMany
    @JoinTable(
            name = "Orders_and_Product",
            joinColumns = { @JoinColumn(name = "order_id")},
            inverseJoinColumns = { @JoinColumn(name = "product_id")})
    private Set<Product> products;
}
