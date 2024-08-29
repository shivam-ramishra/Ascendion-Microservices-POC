package base.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor@AllArgsConstructor
@Getter@Setter
@ToString
@EqualsAndHashCode
public class Client {
    @Id
    private int id;
    private String firstName;
    private String lastName;
}
