package base.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@NoArgsConstructor @AllArgsConstructor
@ToString @EqualsAndHashCode
@Setter @Getter
public class Employee {

    @Id
    private long employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String department;
}
