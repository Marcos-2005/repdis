package com.repdis.driving.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDTO {
    private Long id;
    private String entryDate;
    private String status;
    private String difficulty;
    private Double cost;

    private String clientName;
    private String clientDni;

    private String deviceType;
    private String deviceSerialNumber;

    private String adminName;
}

