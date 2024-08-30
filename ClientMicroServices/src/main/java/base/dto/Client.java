package base.dto;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@NoArgsConstructor@AllArgsConstructor
@Getter@Setter
@ToString
@EqualsAndHashCode
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;

//    @PrePersist
//    public void generateId(){
//        this.id = Integer.parseInt(UUID.randomUUID().toString().substring(0, 3));
//
//    }

}
