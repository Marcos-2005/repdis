package com.repdis.driving.mappers;

import domain.Order;
import com.repdis.driving.dto.OrderResponseDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderDtoMapper {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static OrderResponseDTO toDto(Order order) {
        return OrderResponseDTO.builder()
                .id(order.getId())
                .entryDate(order.getEntryDate().format(FORMATTER))
                .status(order.getStatus().name())
                .difficulty(order.getDifficulty().name())
                .cost(order.getCost())

                .clientName(order.getClient().getName())
                .clientDni(order.getClient().getDni())

                .deviceType(order.getDevice().getType())
                .deviceSerialNumber(order.getDevice().getSerialNumber())

                .adminName(order.getAdmin().getName())
                .build();
    }
}
