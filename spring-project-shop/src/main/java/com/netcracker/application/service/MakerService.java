package com.netcracker.application.service;

import com.netcracker.application.service.model.entity.Maker;
import com.netcracker.application.service.repository.MakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MakerService {
    private final Map<BigInteger, Maker> makers = new HashMap<>();
    private final MakerRepository makerRepository;

    @Autowired
    public MakerService(MakerRepository makerRepository) {
        this.makerRepository = makerRepository;
    }

    private void fill() {
        if (makers.isEmpty()) {
            for (Maker maker : makerRepository.findAll()) {
                makers.put(maker.getId(), maker);
            }
        }
    }

    public List<Maker> getAll() {
        fill();
        return new ArrayList<>(makers.values());
    }

    public Maker getById(BigInteger id) {
        fill();
        return makers.get(id);
    }

    public void add(Maker maker) {
        makerRepository.save(maker);
        makers.clear();
    }

    public void delete(BigInteger id) {
        makerRepository.delete(makers.get(id));
        makers.remove(id);
    }
}
