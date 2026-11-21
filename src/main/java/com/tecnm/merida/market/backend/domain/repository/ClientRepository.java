package com.tecnm.merida.market.backend.domain.repositoy;

import com.tecnm.merida.market.backend.domain.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {

    List<Client> getAll();
    Optional<Client> getClient(String clientId);
    Client save(Client client);
    void delete(String clientId);
}
