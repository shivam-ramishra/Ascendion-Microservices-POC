package base.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ClientEntity {
    @Id
    @GeneratedValue
    @Column(name = "CLIENT_ID")
    private Integer clientId;

    @Column(unique = true, nullable = false)
    @NotEmpty
    @NotBlank
    private String clientName;
}
