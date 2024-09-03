package base.client;

import base.model.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CLIENT-SERVICE", url = "http://localhost:9999/api/client")
public interface ClientConsumer {

    @GetMapping("/client/{clientName}")
    Client findClientByClientName(@PathVariable String clientName);
}