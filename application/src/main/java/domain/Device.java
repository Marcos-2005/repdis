package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Device {
    private Long id;
    private String type;
    private String brand;
    private String model;
    private String serialNumber;
    private String description;
    private String imageUrl;
    private String password;
    private Client client;
}

