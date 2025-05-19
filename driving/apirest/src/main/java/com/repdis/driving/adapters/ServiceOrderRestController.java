package com.repdis.driving.adapters;

import com.repdis.application.ServiceOrderUseCaseImpl;
import com.repdis.driving.dto.ServiceOrderDTO;
import com.repdis.driving.mappers.ServiceOrderDtoMapper;
import domain.ServiceOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class ServiceOrderRestController {

    private final ServiceOrderUseCaseImpl serviceOrderUseCase;

    @GetMapping
    public List<ServiceOrderDTO> getAllOrders() {
        List<ServiceOrder> orders = serviceOrderUseCase.getAllServiceOrders();
        return orders.stream()
                .map(ServiceOrderDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void createOrder(@RequestBody ServiceOrderDTO orderDTO) {
        ServiceOrder domainOrder = ServiceOrderDtoMapper.toDomain(orderDTO);
        serviceOrderUseCase.createServiceOrder(domainOrder);
    }
}
