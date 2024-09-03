package base.service;

import base.exception.ClientNotFoundException;
import base.exception.NoClientsFoundException;
import base.model.Client;

import java.util.List;

public interface ClientService {
    Client addOrUpdateClient(Client client);

    List<Client> getClients() throws NoClientsFoundException;

    boolean deleteClient(int id) throws ClientNotFoundException;

    Client findClientById(int id) throws ClientNotFoundException;

    Client findClientByName(String name) throws ClientNotFoundException;
}
