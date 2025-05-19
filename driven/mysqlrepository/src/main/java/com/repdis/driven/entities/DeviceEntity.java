package com.repdis.driven.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "dispositivos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeviceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String brand;
    private String model;
    @Column(name = "serial_number")
    private String serial;
    private String password;
}
