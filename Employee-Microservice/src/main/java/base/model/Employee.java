package base.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
@Builder
public class Employee {
    private Long employeeId;

    @NonNull
    private String clientId, firstName, lastName, department, email, contact;

    private String lastModifiedBy;

    private double salary;
    private boolean isActive;
    private Date dateOfJoining;
}
