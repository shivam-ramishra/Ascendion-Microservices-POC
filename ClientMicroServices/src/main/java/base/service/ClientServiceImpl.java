package base.service;

import base.dto.Client;
import base.repo.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepo repo;
    @Override
    public Client registerClient(Client client) {
        return repo.save(client);
    }

    @Override
    public List<Client> getClients() {
        return repo.findAll();
    }

    @Override
    public Client updateClient(Client client) {
        return repo.save(client);
    }

    @Override
    public void deleteClient(int id) {
        repo.deleteById(id);
    }

}
