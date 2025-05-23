package com.repdis.application.ports.driven;

import domain.Client;
import domain.Device;
import domain.ServiceOrder;
import java.util.List;

public interface ServiceOrderRepositoryPort {
    List<ServiceOrder> findAll();
    ServiceOrder save(ServiceOrder order);
    Device saveDevice(Device device);
    Client saveClient(Client client);
    void deleteById(Long id);
}