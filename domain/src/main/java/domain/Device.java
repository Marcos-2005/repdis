package domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Device {
    private Long id;
    private String type;
    private String brand;
    private String model;
    private String serialNumber;
    private String password;
    private Client client;
}