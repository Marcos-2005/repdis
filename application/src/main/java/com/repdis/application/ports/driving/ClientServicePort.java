package com.repdis.application.ports.driving;

import domain.Client;
import java.util.List;
import java.util.Optional;

public interface ClientServicePort {
    List<Client> getAllClients();
    void deleteClientById(Long id);
    Optional<Client> findByPhone(String phone);

}
