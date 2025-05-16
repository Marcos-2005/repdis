package com.repdis.application.services;

import domain.Order;
import com.repdis.application.ports.driven.OrderRepositoryPort;
import com.repdis.application.ports.driving.OrderServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements OrderServicePort {

    private final OrderRepositoryPort orderRepositoryPort;

    @Override
    public List<Order> getAllOrders() {
        return orderRepositoryPort.findAll();
    }
}