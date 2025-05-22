package com.repdis.driving.mappers;

import com.repdis.driving.dto.ClientDTO;
import domain.Client;

public class ClientDtoMapper {

    public static Client toDomain(ClientDTO dto) {
        return Client.builder()
                .id(dto.getId())
                .name(dto.getName())
                .dni(dto.getDni())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .address(dto.getAddress())
                .build();
    }

    public static ClientDTO toDto(Client client) {
        return ClientDTO.builder()
                .id(client.getId())
                .name(client.getName())
                .dni(client.getDni())
                .phone(client.getPhone())
                .email(client.getEmail())
                .address(client.getAddress())
                .build();
    }
}
