package base.controller;

import base.client.ClientConsumer;
import base.model.Client;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ClientController {

    private ClientConsumer clientConsumer;

    @GetMapping("/findClientByClientName/{clientName}")
    public ResponseEntity<Client> findClientByClientName(@PathVariable String clientName) {
        return new ResponseEntity<>(clientConsumer.findClientByClientName(clientName), HttpStatus.OK);
    }

}