package com.repdis.driven.mappers;

import com.repdis.driven.entities.AdminEntity;
import com.repdis.driven.entities.ServiceOrderEntity;
import domain.ServiceOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServiceOrderEntityMapper {

    private final ClientEntityMapper clientMapper;
    private final DeviceEntityMapper deviceMapper;
    private final AdminEntityMapper adminMapper;

    public ServiceOrder toDomain(ServiceOrderEntity entity) {
        return ServiceOrder.builder()
                .id(entity.getId())
                .entryDate(entity.getEntryDate())
                .status(entity.getStatus())
                .difficulty(entity.getDifficulty())
                .cost(entity.getCost())
                .adminId(entity.getAdmin() != null ? entity.getAdmin().getId() : null)
                .clientId(entity.getClient() != null ? entity.getClient().getId() : null)
                .deviceId(entity.getDevice() != null ? entity.getDevice().getId() : null)
                .client(entity.getClient() != null ? clientMapper.toDomain(entity.getClient()) : null)
                .device(entity.getDevice() != null ? deviceMapper.toDomain(entity.getDevice()) : null)
                .admin(entity.getAdmin() != null ? adminMapper.toDomain(entity.getAdmin()) : null)
                .build();
    }

    public ServiceOrderEntity toEntity(ServiceOrder order) {
        return ServiceOrderEntity.builder()
                .id(order.getId())
                .entryDate(order.getEntryDate())
                .status(order.getStatus())
                .difficulty(order.getDifficulty())
                .cost(order.getCost())
                .client(order.getClient() != null ? clientMapper.toEntity(order.getClient()) : null)
                .device(order.getDevice() != null ? deviceMapper.toEntity(order.getDevice()) : null)
                .admin(order.getAdminId() != null ? AdminEntity.builder().id(order.getAdminId()).build() : null)
                .build();
    }
}
