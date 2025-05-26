package com.repdis.driving.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeviceDTO {
    private Long id;
    private String type;
    private String brand;
    private String model;
    private String serialNumber;
    private String password;
    private Long clientId;
    private String clientName;
}
