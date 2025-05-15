package com.repdis.driven.adapters;

import com.repdis.application.ports.driven.ServiceOrderRepositoryPort;
import com.repdis.driven.repositories.ServiceOrderJpaRepository;
import com.repdis.driven.mappers.ServiceOrderEntityMapper;
import domain.ServiceOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ServiceOrderRepositoryAdapter implements ServiceOrderRepositoryPort {

    private final ServiceOrderJpaRepository repository;

    @Override
    public List<ServiceOrder> findAll() {
        return repository.findAll().stream()
                .map(ServiceOrderEntityMapper::toDomain)
                .toList();
    }
}
