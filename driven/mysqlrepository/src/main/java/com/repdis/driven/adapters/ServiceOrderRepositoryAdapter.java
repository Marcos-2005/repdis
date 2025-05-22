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

    @Override
    public List<ServiceOrder> findAll() {
        return orderRepository.findAll().stream()
                .map(ServiceOrderEntityMapper::toDomain)
                .toList();
    }

    @Override
    public ServiceOrder save(ServiceOrder order) {
        ClientEntity clientEntity = ClientEntityMapper.toEntity(order.getClient());
        ClientEntity savedClient = clientRepository.save(clientEntity);

        DeviceEntity deviceEntity = DeviceEntityMapper.toEntity(order.getDevice());
        deviceEntity.setClient(savedClient); // IMPORTANTE: asignación
        DeviceEntity savedDevice = deviceRepository.save(deviceEntity);

        ServiceOrderEntity orderEntity = ServiceOrderEntity.builder()
                .entryDate(order.getEntryDate())
                .status(order.getStatus())
                .difficulty(order.getDifficulty())
                .cost(order.getCost())
                .client(savedClient)
                .device(savedDevice)
                .admin(order.getAdminId() != null ?
                        com.repdis.driven.entities.AdminEntity.builder().id(order.getAdminId()).build()
                        : null)
                .build();

        ServiceOrderEntity savedOrder = orderRepository.save(orderEntity);

        return ServiceOrderEntityMapper.toDomain(savedOrder);
    }

    @Override
    public Client saveClient(Client client) {
        ClientEntity entity = ClientEntityMapper.toEntity(client);
        ClientEntity saved = clientRepository.save(entity);
        return ClientEntityMapper.toDomain(saved);
    }

    @Override
    public Device saveDevice(Device device) {
        DeviceEntity entity = DeviceEntityMapper.toEntity(device);
        DeviceEntity saved = deviceRepository.save(entity);
        return DeviceEntityMapper.toDomain(saved);
    }
}
