package com.tecnm.merida.market.backend.domain.service;

import com.tecnm.merida.market.backend.domain.Client;
import com.tecnm.merida.market.backend.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Client> getAll() {
        return clientRepository.getAll();
    }

    public Optional<Client> getClient(String clientId) {
        return clientRepository.getClient(clientId);
    }

    public Optional<Client> getByEmail(String email) {
        return clientRepository.getByEmail(email);
    }

    public Client save(Client client) {
        if (client.getPassword() != null && !client.getPassword().isBlank() && !isBcryptHash(client.getPassword())) {
            client.setPassword(passwordEncoder.encode(client.getPassword()));
        }
        return clientRepository.save(client);
    }

    public Optional<Client> update(String clientId, Client client) {
        return getClient(clientId).map(existing -> {
            client.setClientId(clientId);
            if (client.getPassword() == null || client.getPassword().isBlank()) {
                client.setPassword(existing.getPassword());
            } else if (!isBcryptHash(client.getPassword())) {
                client.setPassword(passwordEncoder.encode(client.getPassword()));
            }
            return clientRepository.save(client);
        });
    }

    public boolean delete(String clientId) {
        if (getClient(clientId).isPresent()) {
            clientRepository.delete(clientId);
            return true;
        } else {
            return false;
        }
    }

    private boolean isBcryptHash(String password) {
        return password.startsWith("$2a$") || password.startsWith("$2b$") || password.startsWith("$2y$");
    }
}
