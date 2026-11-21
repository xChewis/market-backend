package com.tecnm.merida.market.backend.domain.service;

import com.tecnm.merida.market.backend.domain.Client;
import com.tecnm.merida.market.backend.domain.repositoy.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll() {
        return clientRepository.getAll();
    }

    public Optional<Client> getClient(String clientId) {
        return clientRepository.getClient(clientId);
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public Optional<Client> update(String clientId, Client client) {
        return getClient(clientId).map(existing -> {
            client.setClientId(clientId);
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
}
