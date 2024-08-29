package base.controller;

import base.dto.Client;
import base.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    @Autowired
    private ClientService clientService;
    @PostMapping("/add")
    public Client addClient(@RequestBody Client client) {
        return clientService.registerClient(client);
    }
    @PutMapping("/update")
    public Client updateClient(@RequestBody Client client) {
        return clientService.updateClient(client);
    }
    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getClients();
    }
    @DeleteMapping("/delete/{id}")
    public void deleteClient(@PathVariable int id) {
        clientService.deleteClient(id);
    }

}
