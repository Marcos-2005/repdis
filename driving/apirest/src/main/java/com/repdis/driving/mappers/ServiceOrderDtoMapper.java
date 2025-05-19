package com.repdis.driving.mappers;

import com.repdis.driving.dto.ServiceOrderDTO;
import domain.ServiceOrder;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ServiceOrderDtoMapper {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static ServiceOrderDTO toDto(ServiceOrder order) {
        return ServiceOrderDTO.builder()
                .id(order.getId())
                .entryDate(order.getEntryDate().format(formatter))
                .status(order.getStatus())
                .difficulty(order.getDifficulty())
                .cost(order.getCost())
                .deviceId(order.getDeviceId())
                .adminId(order.getAdminId())
                .clientName(order.getClient() != null ? order.getClient().getName() : null)
                .deviceType(order.getDevice() != null ? order.getDevice().getType() : null)
                .build();
    }

    public static ServiceOrder toDomain(ServiceOrderDTO dto) {
        return ServiceOrder.builder()
                .id(dto.getId())
                .entryDate(LocalDateTime.ofInstant(Instant.parse(dto.getEntryDate()), ZoneId.systemDefault()))
                .status(dto.getStatus())
                .difficulty(dto.getDifficulty())
                .cost(dto.getCost())
                .adminId(dto.getAdminId())
                .clientId(dto.getClientId())
                .deviceId(dto.getDeviceId())
                .build();
    }
}
