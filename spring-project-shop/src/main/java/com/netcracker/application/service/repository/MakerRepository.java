package com.netcracker.application.service.repository;

import com.netcracker.application.service.model.entity.Maker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface MakerRepository extends JpaRepository<Maker, BigInteger> {
}
