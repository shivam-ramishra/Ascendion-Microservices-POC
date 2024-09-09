package base.model;

import lombok.Builder;

import java.util.Date;

@Builder
public record Employee(Long employeeId,
                       String clientName,
                       String firstName,
                       String lastName,
                       String department,
                       String email,
                       String contact,
                       String lastModifiedBy,
                       double salary,
                       boolean isActive,
                       Date dateOfJoining) {
}
