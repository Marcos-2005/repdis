package com.repdis.driven.mappers;

import com.repdis.driven.entities.AdminEntity;
import com.repdis.driven.entities.ClientEntity;
import com.repdis.driven.entities.DeviceEntity;
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
        var client = entity.getClient();
        var device = entity.getDevice();
        var admin = entity.getAdmin();

        return ServiceOrder.builder()
                .id(entity.getId())
                .entryDate(entity.getEntryDate())
                .status(entity.getStatus())
                .difficulty(entity.getDifficulty())
                .cost(entity.getCost())
                .clientId(client != null ? client.getId() : null)
                .deviceId(device != null ? device.getId() : null)
                .adminId(admin != null ? admin.getId() : null)
                .client(client != null ? clientMapper.toDomain(client) : null)
                .device(device != null ? deviceMapper.toDomain(device) : null)
                .admin(admin != null ? adminMapper.toDomain(admin) : null)
                .build();
    }

    public ServiceOrderEntity toEntity(ServiceOrder order) {
        return ServiceOrderEntity.builder()
                .id(order.getId())
                .entryDate(order.getEntryDate())
                .status(order.getStatus())
                .difficulty(order.getDifficulty())
                .cost(order.getCost())
                .client(resolveClient(order))
                .device(resolveDevice(order))
                .admin(resolveAdmin(order))
                .build();
    }

    private ClientEntity resolveClient(ServiceOrder order) {
        return order.getClient() != null
                ? clientMapper.toEntity(order.getClient())
                : (order.getClientId() != null ? ClientEntity.builder().id(order.getClientId()).build() : null);
    }

    private DeviceEntity resolveDevice(ServiceOrder order) {
        return order.getDevice() != null
                ? deviceMapper.toEntity(order.getDevice())
                : (order.getDeviceId() != null ? DeviceEntity.builder().id(order.getDeviceId()).build() : null);
    }

    private AdminEntity resolveAdmin(ServiceOrder order) {
        return order.getAdmin() != null
                ? adminMapper.toEntity(order.getAdmin())
                : (order.getAdminId() != null ? AdminEntity.builder().id(order.getAdminId()).build() : null);
    }
}

