package com.repdis.driving.adapters;

import com.repdis.application.ServiceOrderUseCaseImpl;
import com.repdis.driving.dto.ServiceOrderDTO;
import com.repdis.driving.mappers.ServiceOrderDtoMapper;
import domain.ServiceOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @PutMapping
    public ResponseEntity<ServiceOrderDTO> updateOrder(@RequestBody ServiceOrderDTO dto) {
        ServiceOrder updatedOrder = serviceOrderUseCase.createServiceOrder(ServiceOrderDtoMapper.toDomain(dto));
        return ResponseEntity.ok(ServiceOrderDtoMapper.toDto(updatedOrder));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        serviceOrderUseCase.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
