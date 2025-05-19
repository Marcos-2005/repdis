package com.repdis.driven.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String dni;
    private String phone;
    private String email;
    private String address;
}
