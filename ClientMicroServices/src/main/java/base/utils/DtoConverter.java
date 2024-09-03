package base.utils;

import base.dto.ClientEntity;
import base.model.Client;
import org.springframework.beans.BeanUtils;

public class DtoConverter {
    private DtoConverter() {
    }
    public static ClientEntity modelToEntity(Client clientMod) {
        ClientEntity clientEntity = ClientEntity.builder().build();
        BeanUtils.copyProperties(clientMod, clientEntity);
        return clientEntity;
    }
    public static Client entityToModel(ClientEntity clientEntity) {
        Client client = Client.builder().build();
        BeanUtils.copyProperties(clientEntity, client);
        return client;
    }
}
