package com.repdis.application.ports.driven;

import domain.Order;
import java.util.List;

public interface OrderRepositoryPort {
    List<Order> findAll();
}
