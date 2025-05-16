package com.repdis.driven.adapters;

import com.repdis.application.ports.driven.OrderRepositoryPort;
import com.repdis.driven.mappers.OrderEntityMapper;
import com.repdis.driven.repositories.OrderJpaRepository;
import domain.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderRepositoryAdapter implements OrderRepositoryPort {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderEntityMapper orderEntityMapper;

    public OrderRepositoryAdapter(OrderJpaRepository orderJpaRepository, OrderEntityMapper orderEntityMapper) {
        this.orderJpaRepository = orderJpaRepository;
        this.orderEntityMapper = orderEntityMapper;
    }

    @Override
    public List<Order> findAll() {
        return orderJpaRepository.findAll()
                .stream()
                .map(orderEntityMapper::toDomain)
                .toList();
    }
}
