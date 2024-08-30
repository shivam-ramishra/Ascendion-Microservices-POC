package base.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Employee {
    private Long employeeId;
    private String firstName, lastName, department, email, contact, lastModifiedBy;

    private double salary;
    private boolean isActive;
    private Date dateOfJoining;
}
