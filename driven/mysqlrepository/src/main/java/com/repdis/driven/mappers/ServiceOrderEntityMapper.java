package com.repdis.driven.mappers;

import com.repdis.driven.entities.ServiceOrderEntity;
import domain.ServiceOrder;

public class ServiceOrderEntityMapper {

    public static ServiceOrder toDomain(ServiceOrderEntity entity) {
        return ServiceOrder.builder()
                .id(entity.getId())
                .entryDate(entity.getEntryDate())
                .status(entity.getStatus())
                .difficulty(entity.getDifficulty())
                .cost(entity.getCost())
                .deviceId(entity.getDeviceId())
                .adminId(entity.getAdminId())
                .build();
    }
}
