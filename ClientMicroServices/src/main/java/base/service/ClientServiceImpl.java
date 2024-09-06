package base.service;

import base.dto.ClientEntity;
import base.exception.ApplicationException;
import base.exception.ClientNotFoundException;
import base.model.Client;
import base.repo.ClientRepo;
import base.utils.DtoConverter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static base.utils.DtoConverter.entityToModel;
import static base.utils.DtoConverter.modelToEntity;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NO_CONTENT;


@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private static final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);

    @NonNull
    private ClientRepo repo;

    @Override
    public Client addOrUpdateClient(Client client) {
        try {
            ClientEntity clientEntity = modelToEntity(client);
            ClientEntity savedClient = repo.save(clientEntity);
            Client cliModel = entityToModel(savedClient);
            log.info("New client created");
            return cliModel;
        } catch (Exception e) {
            log.error("Something went wrong to save|update employee:: {} with error:: {}", client, e.getMessage());
            return null;
        }
    }

    @Override
    public List<Client> getClients() {
        try {
            return repo.findAll()
                    .stream()
                    .map(DtoConverter::entityToModel)
                    .toList();
        } catch (Exception e) {
            throw ApplicationException.builder().message(e.getMessage()).statusCode(INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public boolean deleteClient(int id) throws ClientNotFoundException {
        return repo.findById(id)
                .map(clientEntity -> {
                    repo.deleteById(id);
                    log.info("Client deleted successfully : {}", id);
                    return true;
                })
                .orElseThrow(() -> ApplicationException.builder()
                        .message("Client Not Found")
                        .statusCode(NO_CONTENT)
                        .build());
    }

    @Override
    public Client findClientById(int id) throws ClientNotFoundException {
        return repo.findById(id)
                .map(DtoConverter::entityToModel)
                .orElseThrow(() -> new ClientNotFoundException(id));
    }

    @Override
    public Client findClientByName(String name) {
        return repo.findByClientName(name)
                .map(DtoConverter::entityToModel)
                .orElseThrow(() -> ApplicationException.builder().message("Client not found.").statusCode(NO_CONTENT).build());
    }

}
