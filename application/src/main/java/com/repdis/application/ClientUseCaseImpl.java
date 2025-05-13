package com.repdis.application;

import domain.Client;
import com.repdis.application.ports.driven.ClientRepositoryPort;
import com.repdis.application.ports.driving.ClientServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientUseCaseImpl implements ClientServicePort {

    private final ClientRepositoryPort clientRepositoryPort;

    @Override
    public List<Client> getAllClients() {
        System.out.println("Fetching all clients from use case...");
        return clientRepositoryPort.findAll();
    }
}
