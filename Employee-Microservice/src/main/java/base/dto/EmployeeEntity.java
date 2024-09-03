package base.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "EMP_INFO")
public class EmployeeEntity {

    @Id
    @GeneratedValue
    @Column(name = "EMP_ID")
    private Long employeeId;

    private String firstName, lastName, clientId;

    @Column(name = "DEPT")
    private String department;

    @Size(min = 7, max = 20)
    private String email;

    @Size(min = 10, max = 10)
    private String contact;

    private double salary;
    private boolean isActive;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date dateOfJoining;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedDate;

    private String lastModifiedBy;
}
