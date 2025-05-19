package com.repdis.driven.mappers;

import com.repdis.driven.entities.ServiceOrderEntity;
import domain.Client;
import domain.Device;
import domain.ServiceOrder;

public class ServiceOrderEntityMapper {

    public static ServiceOrder toDomain(ServiceOrderEntity entity) {
        Client client = null;
        if (entity.getClient() != null) {
            client = Client.builder()
                    .id(entity.getClient().getId())
                    .name(entity.getClient().getName())
                    .build();
        }

        Device device = null;
        if (entity.getDevice() != null) {
            device = Device.builder()
                    .id(entity.getDevice().getId())
                    .type(entity.getDevice().getType())
                    .build();
        }

        return ServiceOrder.builder()
                .id(entity.getId())
                .entryDate(entity.getEntryDate())
                .status(entity.getStatus())
                .difficulty(entity.getDifficulty())
                .cost(entity.getCost())
                .deviceId(entity.getDevice() != null ? entity.getDevice().getId() : null)
                .adminId(entity.getAdmin() != null ? entity.getAdmin().getId() : null)
                .client(client)
                .device(device)
                .build();
    }
}
