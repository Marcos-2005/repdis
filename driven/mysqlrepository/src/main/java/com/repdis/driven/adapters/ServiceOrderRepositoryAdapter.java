package com.repdis.driven.adapters;

import com.repdis.application.ports.driven.ServiceOrderRepositoryPort;
import com.repdis.driven.entities.ClientEntity;
import com.repdis.driven.entities.DeviceEntity;
import com.repdis.driven.entities.ServiceOrderEntity;
import com.repdis.driven.mappers.ClientEntityMapper;
import com.repdis.driven.mappers.DeviceEntityMapper;
import com.repdis.driven.mappers.ServiceOrderEntityMapper;
import com.repdis.driven.repositories.ClientJpaRepository;
import com.repdis.driven.repositories.DeviceJpaRepository;
import com.repdis.driven.repositories.ServiceOrderJpaRepository;
import domain.Client;
import domain.Device;
import domain.ServiceOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ServiceOrderRepositoryAdapter implements ServiceOrderRepositoryPort {

    private final ServiceOrderJpaRepository orderRepository;
    private final ClientJpaRepository clientRepository;
    private final DeviceJpaRepository deviceRepository;

    private final ClientEntityMapper clientEntityMapper;
    private final DeviceEntityMapper deviceEntityMapper;
    private final ServiceOrderEntityMapper serviceOrderEntityMapper;

    @Override
    public List<ServiceOrder> findAll() {
        return orderRepository.findAll().stream()
                .map(serviceOrderEntityMapper::toDomain)
                .toList();
    }

    @Override
    public ServiceOrder save(ServiceOrder order) {
        ClientEntity clientEntity = clientEntityMapper.toEntity(order.getClient());
        ClientEntity savedClient = clientRepository.save(clientEntity);

        DeviceEntity deviceEntity = deviceEntityMapper.toEntity(order.getDevice());
        deviceEntity.setClient(savedClient); // Vinculamos el cliente
        DeviceEntity savedDevice = deviceRepository.save(deviceEntity);

        ServiceOrderEntity orderEntity = serviceOrderEntityMapper.toEntity(order);
        orderEntity.setClient(savedClient);
        orderEntity.setDevice(savedDevice);

        ServiceOrderEntity savedOrder = orderRepository.save(orderEntity);
        return serviceOrderEntityMapper.toDomain(savedOrder);
    }

    @Override
    public Client saveClient(Client client) {
        ClientEntity entity = clientEntityMapper.toEntity(client);
        ClientEntity saved = clientRepository.save(entity);
        return clientEntityMapper.toDomain(saved);
    }

    @Override
    public Device saveDevice(Device device) {
        DeviceEntity entity = deviceEntityMapper.toEntity(device);
        DeviceEntity saved = deviceRepository.save(entity);
        return deviceEntityMapper.toDomain(saved);
    }
}
