package com.repdis.driving.mappers;

import com.repdis.driving.dto.ServiceOrderDTO;
import domain.ServiceOrder;

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
                .build();
    }
}
