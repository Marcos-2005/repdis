package com.repdis.driven.adapters;

import domain.Client;
import lombok.RequiredArgsConstructor;
import com.repdis.driven.mappers.ClientEntityMapper;
import com.repdis.application.ports.driven.ClientRepositoryPort;
import com.repdis.driven.repositories.ClientJpaRepository;
import com.repdis.driven.entities.ClientEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClientRepositoryAdapter implements ClientRepositoryPort {

    private final ClientJpaRepository clientJpaRepository;
    private final ClientEntityMapper clientEntityMapper;

    @Override
    public List<Client> findAll() {
        List<ClientEntity> entities = clientJpaRepository.findAll();
        return entities.stream()
                .map(clientEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        clientJpaRepository.deleteById(id);
    }

    @Override
    public void update(Client client) {
        ClientEntity entity = clientEntityMapper.toEntity(client);
        clientJpaRepository.save(entity); // Save sirve para insertar y actualizar
    }

    @Override
    public Optional<Client> findByPhone(String phone) {
        return clientJpaRepository.findByPhone(phone)
                .map(clientEntityMapper::toDomain);
    }
}

