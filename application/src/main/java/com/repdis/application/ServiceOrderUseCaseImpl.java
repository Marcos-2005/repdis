package com.repdis.application;

import com.repdis.application.ports.driven.ServiceOrderRepositoryPort;
import com.repdis.application.ports.driving.ServiceOrderServicePort;
import domain.ServiceOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceOrderUseCaseImpl implements ServiceOrderServicePort {

    private final ServiceOrderRepositoryPort serviceOrderRepositoryPort;

    @Override
    public List<ServiceOrder> getAllServiceOrders() {
        return serviceOrderRepositoryPort.findAll();
    }
}
