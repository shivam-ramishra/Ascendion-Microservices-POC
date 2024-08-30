package base.controller;

import base.dto.Client;
import base.exception.ClientNotFoundException;
import base.exception.NoClientsFoundException;
import base.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    @Autowired
    private ClientService clientService;
    @PostMapping("/add")
    ResponseEntity<String> addClient(@RequestBody Client client) {
        clientService.registerClient(client);
        return ResponseEntity.ok("Client added Successfully");
    }
    @PutMapping("/update")
    ResponseEntity<String> updateClient(@RequestBody Client client) {
        clientService.updateClient(client);
        return ResponseEntity.ok("Client updated Successfully");
    }
    @GetMapping
    public List<Client> getAllClients() throws NoClientsFoundException {
        return clientService.getClients();
    }
    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteClient(@PathVariable int id) throws ClientNotFoundException {
        clientService.deleteClient(id);
        return ResponseEntity.ok("Client Deleted Successfully");
    }

}
