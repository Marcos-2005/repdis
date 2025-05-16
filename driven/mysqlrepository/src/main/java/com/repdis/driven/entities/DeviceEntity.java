package com.repdis.driven.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "dispositivos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeviceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type;

    private String brand;
    private String model;

    @Column(name = "serial_number", nullable = false, unique = true)
    private String serialNumber;

    private String description;
    private String password;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client;
}

