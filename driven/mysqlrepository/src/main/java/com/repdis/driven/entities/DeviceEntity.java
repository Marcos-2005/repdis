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
    private String serialNumber;
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private ClientEntity client;

    @Column(name = "client_id", insertable = false, updatable = false)
    private Long clientId;
}
