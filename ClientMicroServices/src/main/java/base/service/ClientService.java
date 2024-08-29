package base.service;

import base.dto.Client;

import java.util.List;

public interface ClientService {
    public Client registerClient(Client client);
    public List<Client> getClients();
    public Client updateClient(Client client);
    public void deleteClient(int id);
}
