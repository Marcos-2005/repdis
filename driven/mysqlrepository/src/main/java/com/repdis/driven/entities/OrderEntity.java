package com.repdis.driven.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ordenes_servicios")
@Getter
@Setter
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entry_date")
    private LocalDateTime entryDate;

    @Column(name = "status")
    private String status;

    @Column(name = "difficulty")
    private String difficulty;

    @Column(name = "cost")
    private BigDecimal cost;

    // Relaciones básicas
    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientEntity client;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private DeviceEntity device;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private AdminEntity admin;
}
