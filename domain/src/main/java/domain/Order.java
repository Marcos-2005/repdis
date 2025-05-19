package domain;


import domain.enums.OrderDifficulty;
import domain.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id;
    private LocalDateTime entryDate;
    private OrderStatus status;
    private OrderDifficulty difficulty;
    private Double cost;

    private Client client;        // datos del cliente
    private Device device;        // datos del dispositivo
    private Admin admin;          // técnico que la creó
}

