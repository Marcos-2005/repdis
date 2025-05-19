package com.repdis.driven.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ordenes_servicios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entry_date")
    private LocalDateTime entryDate;

    private String status;
    private String difficulty;
    private Double cost;

    // Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", referencedColumnName = "id", insertable = false, updatable = false)
    private DeviceEntity device;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", referencedColumnName = "id", insertable = false, updatable = false)
    private AdminEntity admin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id", insertable = false, updatable = false)
    private ClientEntity client;

    @Column(name = "device_id")
    private Long deviceId;

    @Column(name = "admin_id")
    private Long adminId;

    @Column(name = "client_id")
    private Long clientId;
}
