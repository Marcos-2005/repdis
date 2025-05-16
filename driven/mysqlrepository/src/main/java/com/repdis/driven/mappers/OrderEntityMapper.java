package com.repdis.driven.mappers;

import com.repdis.driven.entities.OrderEntity;
import domain.Order;
import domain.enums.OrderDifficulty;
import domain.enums.OrderStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderEntityMapper {

    public Order toDomain(OrderEntity entity) {
        return new Order(
                entity.getId(),
                entity.getEntryDate(),
                OrderStatus.valueOf(entity.getStatus()),
                OrderDifficulty.valueOf(entity.getDifficulty()),
                entity.getCost() != null ? entity.getCost().doubleValue() : null,
                null,
                null,
                null
        );
    }

    public OrderEntity toEntity(Order domain) {
        OrderEntity entity = new OrderEntity();
        entity.setId(domain.getId());
        entity.setEntryDate(domain.getEntryDate());
        entity.setStatus(domain.getStatus().name());
        entity.setDifficulty(domain.getDifficulty().name());
        entity.setCost(domain.getCost() != null ? BigDecimal.valueOf(domain.getCost()) : null);
        return entity;
    }
}
