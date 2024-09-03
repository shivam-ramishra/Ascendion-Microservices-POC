package base.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
public class Client {
    private int id;
    private String firstName;
    private String lastName;

//    @PrePersist
//    public void generateId(){
//        this.id = Integer.parseInt(UUID.randomUUID().toString().substring(0, 3));
//
//    }

}
