package base.service;

import base.dto.ClientEntity;
import base.exception.ClientNotFoundException;
import base.exception.NoClientsFoundException;
import base.model.Client;
import base.repo.ClientRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private ClientRepo repo;

    @InjectMocks
    private ClientServiceImpl service;

    @Test
    void saveOrUpdate_success(){
        var reqModel = Client.builder()
                .firstName("FNAME")
                .lastName("LNAME")
                .build();

        var respEntity = ClientEntity.builder()
                .id(1)
                .firstName("FNAME")
                .lastName("LNAME")
                .build();

        var respModel = Client.builder()
                .id(1)
                .firstName("FNAME")
                .lastName("LNAME")
                .build();

        when(repo.save(any(ClientEntity.class)))
                .thenReturn(respEntity);

        Client savedCLient = service.addOrUpdateClient(reqModel);

        assertNotNull(savedCLient);
        assertEquals(respModel.getId(),savedCLient.getId());
    }

    @Test
    void saveOrUpdate_failure(){
        var reqModel = Client.builder()
                .firstName("FNAME")
                .lastName("LNAME")
                .build();
        when(repo.save(any(ClientEntity.class)))
                .thenThrow(new RuntimeException());

        Client savedClient = service.addOrUpdateClient(reqModel);
        assertNull(savedClient);
    }

    @Test
    void deleteClient_success() throws ClientNotFoundException {
        var respEntity = ClientEntity.builder()
                .id(1)
                .firstName("FNAME")
                .lastName("LNAME")
                .build();

        when(repo.findById(1))
                .thenReturn(ofNullable(respEntity));
        doNothing().when(repo).deleteById(any());
        boolean deleted = service.deleteClient(1);
        assertTrue(deleted);
    }

    @Test
    void deleteClient_ClientNotFound() throws ClientNotFoundException {
        var respEntity = ClientEntity.builder()
                .id(1)
                .firstName("FNAME")
                .lastName("LNAME")
                .build();

        when(repo.findById(1))
                .thenReturn(ofNullable(respEntity));
        doThrow(new RuntimeException()).when(repo).deleteById(any());
        //new ClientNotFoundException

        boolean deleted = service.deleteClient(1);
        assertFalse(deleted);
    }

    @Test
    void findAll_success() throws NoClientsFoundException {
        var respEntity1 = ClientEntity.builder()
                .id(1)
                .firstName("FNAME")
                .lastName("LNAME")
                .build();
        var respEntity2 = ClientEntity.builder()
                .id(2)
                .firstName("FNAME2")
                .lastName("LNAME2")
                .build();
        var respEntity3 = ClientEntity.builder()
                .id(3)
                .firstName("FNAME3")
                .lastName("LNAME3")
                .build();

        List<ClientEntity> mockList = List.of(respEntity1,respEntity2,respEntity3);
        when(repo.findAll())
                .thenReturn(mockList);

        List<Client> all = service.getClients();

        assertFalse(all.isEmpty());
        assertEquals(3, all.size());
        assertEquals(1,all.get(0).getId());
    }

    @Test
    void findAll_failure() throws NoClientsFoundException {
        when(repo.findAll())
                .thenReturn(emptyList());
        List<Client> all = service.getClients();
        assertTrue(all.isEmpty());
    }

    @Test
    void findClientById_success() throws ClientNotFoundException {
        var entity = ClientEntity.builder()
                .id(1)
                .firstName("FNAME")
                .lastName("LNAME")
                .build();

        when(repo.findById(1))
                .thenReturn(ofNullable(entity));

        Client cli = service.findClientById(1);
        assertEquals(101,cli.getId());
    }

    @Test
    void findClientById_failure(){
        when(repo.findById(1))
                .thenReturn(empty());

        assertThrows(ClientNotFoundException.class,
                ()-> service.findClientById(1));
    }
}
