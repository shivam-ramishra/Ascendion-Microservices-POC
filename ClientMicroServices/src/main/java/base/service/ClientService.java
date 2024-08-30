package base.service;

import base.dto.Client;
import base.exception.ClientNotFoundException;
import base.exception.NoClientsFoundException;

import java.util.List;

public interface ClientService {
    public Client registerClient(Client client);
    public List<Client> getClients() throws NoClientsFoundException;
    public Client updateClient(Client client);
    public void deleteClient(int id) throws ClientNotFoundException;
}
