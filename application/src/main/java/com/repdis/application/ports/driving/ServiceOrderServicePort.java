package com.repdis.application.ports.driving;

import domain.ServiceOrder;
import java.util.List;

public interface ServiceOrderServicePort {
    List<ServiceOrder> getAllServiceOrders();
    ServiceOrder createServiceOrder(ServiceOrder order);
}
