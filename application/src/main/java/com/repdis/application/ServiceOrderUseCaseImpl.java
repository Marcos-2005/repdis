package com.repdis.application;

import com.repdis.application.ports.driven.ServiceOrderRepositoryPort;
import com.repdis.application.ports.driving.ServiceOrderServicePort;
import domain.Client;
import domain.Device;
import domain.ServiceOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceOrderUseCaseImpl implements ServiceOrderServicePort {

    private final ServiceOrderRepositoryPort serviceOrderRepository;

    @Override
    public List<ServiceOrder> getAllServiceOrders() {
        return serviceOrderRepository.findAll();
    }

    @Override
    public ServiceOrder createServiceOrder(ServiceOrder order) {


        Client savedClient = order.getClient();
        if (savedClient != null && savedClient.getId() == null) {
            savedClient = serviceOrderRepository.saveClient(savedClient);
        }
        order.setClient(savedClient);
        order.setClientId(savedClient.getId());


        Device originalDevice = order.getDevice();
        if (originalDevice != null) {
            Device deviceWithClient = originalDevice.toBuilder()
                    .client(savedClient)
                    .build();

            Device savedDevice = serviceOrderRepository.saveDevice(deviceWithClient);
            order.setDevice(savedDevice);
            order.setDeviceId(savedDevice.getId());
        }


        if (order.getAdminId() == null) {
            throw new IllegalArgumentException("adminId es obligatorio");
        }

        return serviceOrderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        serviceOrderRepository.deleteById(id);
    }
}
