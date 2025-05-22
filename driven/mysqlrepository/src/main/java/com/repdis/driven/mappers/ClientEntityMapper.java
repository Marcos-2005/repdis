package com.repdis.driven.mappers;

import domain.Client;
import com.repdis.driven.entities.ClientEntity;

public class ClientEntityMapper {

     private ClientEntityMapper() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static Client toDomain(ClientEntity entity) {
        return new Client(
                entity.getId(),
                entity.getName(),
                entity.getDni(),
                entity.getPhone(),
                entity.getEmail(),
                entity.getAddress()
        );
    }

    public static ClientEntity toEntity(Client client) {
        return ClientEntity.builder()
                .id(client.getId())
                .name(client.getName())
                .email(client.getEmail())
                .dni(client.getDni())
                .address(client.getAddress())
                .phone(client.getPhone())
                .build();
    }
}
