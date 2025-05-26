package com.repdis.driven.mappers;

import com.repdis.driven.entities.ClientEntity;
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
                .clientId(
                        entity.getClient() != null
                                ? entity.getClient().getId()
                                : entity.getClientId()
                )
                .client(entity.getClient() != null
                        ? clientEntityMapper.toDomain(entity.getClient())
                        : null)
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
                .client(resolveClient(device))
                .build();
    }

    private ClientEntity resolveClient(Device device) {
        if (device.getClient() != null) {
            return clientEntityMapper.toEntity(device.getClient());
        } else if (device.getClientId() != null) {
            return ClientEntity.builder().id(device.getClientId()).build();
        }
        return null;
    }
}
