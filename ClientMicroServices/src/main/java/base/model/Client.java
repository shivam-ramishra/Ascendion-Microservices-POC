package base.model;

import lombok.Builder;

@Builder
public record Client(Integer clientId, String clientName) {
}
