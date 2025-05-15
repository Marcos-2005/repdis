package com.repdis.driving.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderDTO {
    private Long id;
    private LocalDateTime entryDate;
    private String status;
    private String difficulty;
    private BigDecimal cost;

    // Datos del cliente
    private String clientName;
    private String clientDni;

    // Datos del dispositivo
    private String deviceType;
    private String deviceBrand;
    private String deviceModel;
    private String deviceSerialNumber;

    // Nombre del administrador que creó la orden
    private String adminName;
}
