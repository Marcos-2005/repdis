package com.repdis.driven.adapters;

import com.repdis.application.ports.driven.AdminRepositoryPort;
import com.repdis.driven.entities.AdminEntity;
import com.repdis.driven.repositories.AdminJpaRepository;
import domain.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AdminRepositoryAdapter implements AdminRepositoryPort {

    private final AdminJpaRepository repository;

    @Override
    public List<Admin> findAll() {
        return repository.findAll().stream()
                .map(entity -> new Admin(entity.getId(), entity.getName(), entity.getUsername(), null))
                .collect(Collectors.toList());
    }
}
