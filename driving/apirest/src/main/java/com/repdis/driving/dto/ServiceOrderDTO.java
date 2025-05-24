package com.repdis.driving.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ServiceOrderDTO {
    private Long id;
    private String entryDate;
    private String status;
    private String difficulty;
    private Double cost;
    private Long adminId;
    private String adminName;


    private ClientDTO client;
    private DeviceDTO device;
}
