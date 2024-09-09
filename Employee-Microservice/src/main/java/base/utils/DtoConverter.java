package base.utils;

import base.dto.EmployeeEntity;
import base.model.Employee;

public class DtoConverter {

    private DtoConverter() {
    }

    public static EmployeeEntity modelToEntity(Employee model) {
        return EmployeeEntity.builder()
                .employeeId(model.employeeId())
                .firstName(model.firstName())
                .lastName(model.lastName())
                .dateOfJoining(model.dateOfJoining())
                .clientName(model.clientName())
                .salary(model.salary())
                .contact(model.contact())
                .email(model.email())
                .isActive(model.isActive())
                .department(model.department())
                .lastModifiedBy(model.lastModifiedBy())
                .lastModifiedBy(model.lastModifiedBy())
                .build();
    }

    public static Employee entityToModel(EmployeeEntity entity) {
        return Employee.builder()
                .employeeId(entity.getEmployeeId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .dateOfJoining(entity.getDateOfJoining())
                .salary(entity.getSalary())
                .department(entity.getDepartment())
                .contact(entity.getContact())
                .isActive(entity.isActive())
                .lastModifiedBy(entity.getLastModifiedBy())
                .build();
    }
}
