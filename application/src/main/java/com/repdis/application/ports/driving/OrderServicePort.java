package com.repdis.application.ports.driving;

import domain.Order;
import java.util.List;

public interface OrderServicePort {
    List<Order> getAllOrders();
}
