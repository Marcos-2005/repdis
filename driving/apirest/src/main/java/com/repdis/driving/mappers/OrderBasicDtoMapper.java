package com.repdis.driving.mappers;

import com.repdis.driving.dto.OrderDTO;
import domain.Order;
import domain.enums.OrderDifficulty;
import domain.enums.OrderStatus;

import java.math.BigDecimal;

public class OrderBasicDtoMapper {

    public static Order toDomain(OrderDTO dto) {
        return new Order(
                dto.getId(),
                dto.getEntryDate(),
                OrderStatus.valueOf(dto.getStatus()),
                OrderDifficulty.valueOf(dto.getDifficulty()),
                dto.getCost() != null ? dto.getCost().doubleValue() : null,
                null,  // client se gestiona aparte
                null,  // device tmb
                null   // admin tmb
        );
    }

    public static OrderDTO toDto(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setEntryDate(order.getEntryDate());
        dto.setStatus(order.getStatus().name());
        dto.setDifficulty(order.getDifficulty().name());
        dto.setCost(order.getCost() != null ? BigDecimal.valueOf(order.getCost()) : null);
        dto.setClientName(null);
        dto.setClientDni(null);
        dto.setDeviceType(null);
        dto.setDeviceBrand(null);
        dto.setDeviceModel(null);
        dto.setDeviceSerialNumber(null);
        dto.setAdminName(null);

        return dto;
    }
}
