package base.service;

import base.dto.Client;
import base.exception.ClientNotFoundException;
import base.exception.NoClientsFoundException;
import base.repo.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepo repo;
    @Override
    public Client registerClient(Client client) {
        return repo.save(client);
    }

    @Override
    public List<Client> getClients() throws NoClientsFoundException {
        List<Client> clients = repo.findAll();
        if (clients.isEmpty()) {
            throw new NoClientsFoundException(clients);
        }
        return clients;
    }

    @Override
    public Client updateClient(Client client) {
        return repo.save(client);
    }

    @Override
    public void deleteClient(int id) throws ClientNotFoundException {
        Optional<Client> find = repo.findById(id);
        if (find.isEmpty()) {
            throw new ClientNotFoundException(id);
        }
        repo.delete(find.get());
    }

}
