package com.repdis.driven.mappers;

import com.repdis.driven.entities.DeviceEntity;
import domain.Device;

public class DeviceEntityMapper {

    public static Device toDomain(DeviceEntity entity) {
        return Device.builder()
                .id(entity.getId())
                .type(entity.getType())
                .brand(entity.getBrand())
                .model(entity.getModel())
                .serialNumber(entity.getSerialNumber())
                .password(entity.getPassword())
                .build();
    }

    public static DeviceEntity toEntity(Device device) {
        return DeviceEntity.builder()
                .id(device.getId())
                .type(device.getType())
                .brand(device.getBrand())
                .model(device.getModel())
                .serialNumber(device.getSerialNumber())
                .password(device.getPassword())
                .client(device.getClient() != null ? ClientEntityMapper.toEntity(device.getClient()) : null)
                .build();
    }
}
