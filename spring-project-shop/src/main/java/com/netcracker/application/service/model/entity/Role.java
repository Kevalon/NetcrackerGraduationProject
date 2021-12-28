package com.netcracker.application.service.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.security.core.GrantedAuthority;

import java.math.BigInteger;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Role implements GrantedAuthority {
    @Id
    @Column("id")
    private BigInteger id;
    @Column("name")
    private String name;
    @Column("authority")
    private String authority;
}
