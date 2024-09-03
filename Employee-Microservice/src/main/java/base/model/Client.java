package base.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Client {
    private Integer clientId;
    private String clientName;

}
