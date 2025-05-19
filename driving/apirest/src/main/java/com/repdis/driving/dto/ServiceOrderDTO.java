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
    private Long deviceId;
    private Long adminId;
    private Long clientId;
    private String clientName;
    private String deviceType;
}
