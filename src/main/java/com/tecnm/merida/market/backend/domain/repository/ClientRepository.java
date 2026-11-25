package com.tecnm.merida.market.backend.domain.repository;

import com.tecnm.merida.market.backend.domain.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {

    List<Client> getAll();
    Optional<Client> getClient(String clientId);
    Optional<Client> getByEmail(String email);
    Client save(Client client);
    void delete(String clientId);
}
