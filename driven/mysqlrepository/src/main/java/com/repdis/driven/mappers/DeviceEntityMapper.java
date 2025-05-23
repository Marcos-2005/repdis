package com.repdis.driven.mappers;

import com.repdis.driven.entities.DeviceEntity;
import domain.Device;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeviceEntityMapper {

    private final ClientEntityMapper clientEntityMapper;

    public Device toDomain(DeviceEntity entity) {
        return Device.builder()
                .id(entity.getId())
                .type(entity.getType())
                .brand(entity.getBrand())
                .model(entity.getModel())
                .serialNumber(entity.getSerialNumber())
                .password(entity.getPassword())
                .build();
    }

    public DeviceEntity toEntity(Device device) {
        return DeviceEntity.builder()
                .id(device.getId())
                .type(device.getType())
                .brand(device.getBrand())
                .model(device.getModel())
                .serialNumber(device.getSerialNumber())
                .password(device.getPassword())
                .client(device.getClient() != null ? clientEntityMapper.toEntity(device.getClient()) : null)
                .build();
    }
}

