package com.repdis.driving.mappers;

import com.repdis.driving.dto.ServiceOrderDTO;
import domain.ServiceOrder;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class ServiceOrderDtoMapper {

    private static final DateTimeFormatter isoFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    public static ServiceOrderDTO toDto(ServiceOrder order) {
        return ServiceOrderDTO.builder()
                .id(order.getId())
                .entryDate(order.getEntryDate().atOffset(OffsetDateTime.now().getOffset()).format(isoFormatter))
                .status(order.getStatus())
                .difficulty(order.getDifficulty())
                .cost(order.getCost())
                .adminName(order.getAdmin() != null ? order.getAdmin().getName() : null)
                .client(ClientDtoMapper.toDto(order.getClient()))
                .device(DeviceDtoMapper.toDto(order.getDevice()))
                .build();
    }

    public static ServiceOrder toDomain(ServiceOrderDTO dto) {
        return ServiceOrder.builder()
                .id(dto.getId())
                .entryDate(dto.getEntryDate() != null ?
                        OffsetDateTime.parse(dto.getEntryDate(), isoFormatter).toLocalDateTime() :
                        LocalDateTime.now())
                .status(dto.getStatus())
                .difficulty(dto.getDifficulty())
                .cost(dto.getCost())
                .adminId(dto.getAdminId())
                .client(ClientDtoMapper.toDomain(dto.getClient()))
                .device(DeviceDtoMapper.toDomain(dto.getDevice()))
                .build();
    }
}