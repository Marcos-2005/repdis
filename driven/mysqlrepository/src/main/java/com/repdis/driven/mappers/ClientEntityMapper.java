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
        return new ClientEntity(
                client.getId(),
                client.getName(),
                client.getDni(),
                client.getPhone(),
                client.getEmail(),
                client.getAddress()
        );
    }
}
