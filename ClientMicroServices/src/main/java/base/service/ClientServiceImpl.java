package base.service;

import base.dto.ClientEntity;
import base.model.Client;
import base.exception.ClientNotFoundException;
import base.exception.NoClientsFoundException;
import base.repo.ClientRepo;
import base.utils.DtoConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static base.utils.DtoConverter.entityToModel;
import static base.utils.DtoConverter.modelToEntity;


@Service
public class ClientServiceImpl implements ClientService {

    private static final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);
    @Autowired
    ClientRepo repo;

    @Override
    public Client addOrUpdateClient(Client client) {
        try{
            ClientEntity clientEntity = modelToEntity(client);
            ClientEntity savedClient = repo.save(clientEntity);
            Client cliModel = entityToModel(savedClient);
            log.info("New client created");
            return cliModel;
        } catch (Exception e){
            log.error("Something went wrong to save|update employee:: {} with error:: {}", client, e.getMessage());
            return null;
        }
    }

    @Override
    public List<Client> getClients() throws NoClientsFoundException {
        try {
            return repo.findAll()
                    .stream()
                    .map(DtoConverter::entityToModel)
                    .toList();
        }catch (Exception e){
            log.error("Something went wrong to get client list", e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public boolean deleteClient(int id) throws ClientNotFoundException {
        Client client = findClientById(id);
        if(client != null){
            try{
            repo.deleteById(client.getId());
            log.info("Client deleted successfully", id);
            return true;
        }catch (Exception e){
            log.error("Something went wrong to delete client:: {} with error:: {}", client, e.getMessage());
            return false;
            }
        }
        throw new ClientNotFoundException(id);
    }

    @Override
    public Client findClientById(int id) throws ClientNotFoundException {
        return repo.findById(id)
                .map(DtoConverter::entityToModel)
                .orElseThrow(() -> new ClientNotFoundException(id));
    }

}
