package com.repdis.application.ports.driven;

import domain.Client;
import java.util.List;

public interface ClientRepositoryPort {
    List<Client> findAll();
    void update(Client client);
    void deleteById(Long id);

}
