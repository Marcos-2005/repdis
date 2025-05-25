package com.repdis.driving.adapters;

import com.repdis.driving.dto.ClientDTO;
import lombok.RequiredArgsConstructor;
import com.repdis.driving.mappers.ClientDtoMapper;
import com.repdis.application.services.ClientService;
import domain.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientRestController {

    private final ClientService clientService;

    @GetMapping
    public List<ClientDTO> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        return clients.stream()
                .map(ClientDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping
    public ResponseEntity<Void> updateClient(@RequestBody ClientDTO clientDTO) {
        Client client = ClientDtoMapper.toDomain(clientDTO); // ← CONVERSIÓN AQUÍ
        clientService.updateClient(client);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClientById(id);
        return ResponseEntity.noContent().build();
    }
}
