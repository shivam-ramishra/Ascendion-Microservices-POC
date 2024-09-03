package base.controller;

import base.exception.ClientNotFoundException;
import base.exception.NoClientsFoundException;
import base.model.Client;
import base.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/addOrUpdate")
    public ResponseEntity<?> addOrUpdate(@RequestBody Client client) {
        Client client1 = clientService.addOrUpdateClient(client);
        return (client1 != null) ?
                new ResponseEntity<>(client1, HttpStatus.CREATED) :
                new ResponseEntity<>("Couldn't save client details.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/findAll")
    public List<Client> getAllClients() throws NoClientsFoundException {
        return clientService.getClients();
    }

    @GetMapping("/client/{name}")
    public Client findClientByName(@PathVariable String name) throws ClientNotFoundException {
        return clientService.findClientByName(name);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteClient(@PathVariable int id) throws ClientNotFoundException {
        boolean deleted = clientService.deleteClient(id);
        if (deleted) {
            return ResponseEntity.ok("Client Deleted Successfully, clientId:: " + id);
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
