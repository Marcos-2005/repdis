package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceOrder {
    private Long id;
    private LocalDateTime entryDate;
    private String status;
    private String difficulty;
    private Double cost;
    private Long deviceId;
    private Long adminId;
    private Long clientId;
    private Client client;
    private Device device;

}
