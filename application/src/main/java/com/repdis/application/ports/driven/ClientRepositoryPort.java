package com.repdis.application.ports.driven;

import domain.Client;
import java.util.List;
import java.util.Optional;

public interface ClientRepositoryPort {
    List<Client> findAll();
    void update(Client client);
    void deleteById(Long id);
    Optional<Client> findByPhone(String phone);


}
