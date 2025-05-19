package com.repdis.driving.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceOrderDTO {
    private Long id;
    private String entryDate;
    private String status;
    private String difficulty;
    private Double cost;
    private Long deviceId;
    private Long adminId;

    private String clientName;
    private String deviceType;
}
