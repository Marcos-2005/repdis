package com.repdis.application.ports.driving;

import domain.Client;
import java.util.List;

public interface ClientServicePort {
    List<Client> getAllClients();
}
