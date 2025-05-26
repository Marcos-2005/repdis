package com.repdis.driving.mappers;

import com.repdis.driving.dto.DeviceDTO;
import domain.Device;

public class DeviceDtoMapper {

    public static Device toDomain(DeviceDTO dto) {
        return Device.builder()
                .id(dto.getId())
                .type(dto.getType())
                .brand(dto.getBrand())
                .model(dto.getModel())
                .serialNumber(dto.getSerialNumber())
                .password(dto.getPassword())
                .clientId(dto.getClientId())
                .build();
    }

    public static DeviceDTO toDto(Device device) {
        return DeviceDTO.builder()
                .id(device.getId())
                .type(device.getType())
                .brand(device.getBrand())
                .model(device.getModel())
                .serialNumber(device.getSerialNumber())
                .password(device.getPassword())
                .clientId(device.getClientId())
                .clientName(device.getClient() != null ? device.getClient().getName() : null)
                .build();
    }
}
