package base.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Employee {
    private Long employeeId;

    private String clientName;

    private String firstName;

    private String lastName;

    private String department;

    private String email;

    private String contact;

    private String lastModifiedBy;

    private double salary;

    private boolean isActive;

    private Date dateOfJoining;
}
