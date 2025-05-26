package com.repdis.application;

import domain.Client;
import com.repdis.application.ports.driven.ClientRepositoryPort;
import com.repdis.application.ports.driving.ClientServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientUseCaseImpl implements ClientServicePort {

    private final ClientRepositoryPort clientRepositoryPort;

    @Override
    public List<Client> getAllClients() {
        System.out.println("Fetching all clients from use case...");
        return clientRepositoryPort.findAll();
    }

    @Override
    public void deleteClientById(Long id) {
        clientRepositoryPort.deleteById(id);
    }

    @Override
    public Optional<Client> findByPhone(String phone) {
        return clientRepositoryPort.findByPhone(phone);
    }
}
