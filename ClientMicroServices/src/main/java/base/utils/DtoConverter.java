package base.utils;

import base.dto.ClientEntity;
import base.model.Client;

public class DtoConverter {
    private DtoConverter() {
    }

    public static ClientEntity modelToEntity(Client model) {
        return ClientEntity.builder()
                .clientId(model.clientId())
                .clientName(model.clientName())
                .build();
    }

    public static Client entityToModel(ClientEntity entity) {
        return Client.builder()
                .clientId(entity.getClientId())
                .clientName(entity.getClientName())
                .build();
    }
}
