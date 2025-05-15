package com.repdis.application.ports.driven;

import domain.ServiceOrder;
import java.util.List;

public interface ServiceOrderRepositoryPort {
    List<ServiceOrder> findAll();
}