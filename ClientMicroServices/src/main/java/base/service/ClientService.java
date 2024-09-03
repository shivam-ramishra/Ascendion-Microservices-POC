package base.service;

import base.dto.ClientEntity;
import base.model.Client;
import base.exception.ClientNotFoundException;
import base.exception.NoClientsFoundException;

import java.util.List;

public interface ClientService {
    Client addOrUpdateClient(Client client);
    public List<Client> getClients() throws NoClientsFoundException;
    public boolean deleteClient(int id) throws ClientNotFoundException;
    Client findClientById(int id) throws ClientNotFoundException;
}
