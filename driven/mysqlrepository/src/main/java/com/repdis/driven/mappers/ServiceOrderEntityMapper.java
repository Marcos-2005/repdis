package com.repdis.driven.mappers;

import com.repdis.driven.entities.AdminEntity;
import com.repdis.driven.entities.ServiceOrderEntity;
import domain.ServiceOrder;

public class ServiceOrderEntityMapper {

    private ServiceOrderEntityMapper() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static ServiceOrder toDomain(ServiceOrderEntity entity) {
        return ServiceOrder.builder()
                .id(entity.getId())
                .entryDate(entity.getEntryDate())
                .status(entity.getStatus())
                .difficulty(entity.getDifficulty())
                .cost(entity.getCost())
                .adminId(entity.getAdmin() != null ? entity.getAdmin().getId() : null)
                .clientId(entity.getClient() != null ? entity.getClient().getId() : null)
                .deviceId(entity.getDevice() != null ? entity.getDevice().getId() : null)
                .client(entity.getClient() != null ? ClientEntityMapper.toDomain(entity.getClient()) : null)
                .device(entity.getDevice() != null ? DeviceEntityMapper.toDomain(entity.getDevice()) : null)
                .admin(entity.getAdmin() != null ? AdminEntityMapper.toDomain(entity.getAdmin()) : null)
                .build();
    }

    public static ServiceOrderEntity toEntity(ServiceOrder order) {
        return ServiceOrderEntity.builder()
                .id(order.getId())
                .entryDate(order.getEntryDate())
                .status(order.getStatus())
                .difficulty(order.getDifficulty())
                .cost(order.getCost())
                .client(order.getClient() != null ? ClientEntityMapper.toEntity(order.getClient()) : null)
                .device(order.getDevice() != null ? DeviceEntityMapper.toEntity(order.getDevice()) : null)
                .admin(order.getAdminId() != null ? AdminEntity.builder().id(order.getAdminId()).build() : null)
                .build();
    }
}
