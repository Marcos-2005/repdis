package com.repdis.application.services;

import domain.Client;
import com.repdis.application.ports.driven.ClientRepositoryPort;
import com.repdis.application.ports.driving.ClientServicePort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService implements ClientServicePort {

    private final ClientRepositoryPort clientRepositoryPort;

    public ClientService(ClientRepositoryPort clientRepositoryPort) {
        this.clientRepositoryPort = clientRepositoryPort;
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepositoryPort.findAll();
    }

    @Override
    public void deleteClientById(Long id) {
        clientRepositoryPort.deleteById(id);
    }
}
