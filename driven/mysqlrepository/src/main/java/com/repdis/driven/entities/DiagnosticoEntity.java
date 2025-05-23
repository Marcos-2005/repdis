package com.repdis.driven.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "diagnosticos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiagnosticoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_order_id", referencedColumnName = "id", nullable = false)
    private ServiceOrderEntity serviceOrder;

    @Column(name = "service_order_id", insertable = false, updatable = false)
    private Long serviceOrderId;
}
