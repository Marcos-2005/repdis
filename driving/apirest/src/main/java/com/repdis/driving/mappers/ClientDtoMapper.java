package com.repdis.driving.mappers;

import domain.Client;
import com.repdis.driving.dto.ClientDTO;

public class ClientDtoMapper {
    public static ClientDTO toDto(Client client) {
        return new ClientDTO(
                client.getId(),
                client.getName(),
                client.getDni(),
                client.getPhone(),
                client.getEmail(),
                client.getAddress()
        );
    }

    public static Client toDomain(ClientDTO dto) {
        return new Client(
                dto.getId(),
                dto.getName(),
                dto.getDni(),
                dto.getPhone(),
                dto.getEmail(),
                dto.getAddress()
        );
    }
}
