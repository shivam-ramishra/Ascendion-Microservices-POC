package base.utils;

import base.dto.EmployeeEntity;
import base.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

@ExtendWith(MockitoExtension.class)
class DtoConverterTest {

    Employee reqModel = Employee.builder()
            .employeeId(101L)
            .firstName("FNAME")
            .lastName("LNAME")
            .email("demo@email.com")
            .contact("123456789")
            .salary(123.4)
            .department("SYS")
            .isActive(true)
            .dateOfJoining(new Date())
            .build();

    EmployeeEntity respEntity = EmployeeEntity.builder()
            .employeeId(101L)
            .firstName("FNAME")
            .lastName("LNAME")
            .email("demo@email.com")
            .contact("123456789")
            .salary(123.4)
            .department("SYS")
            .isActive(true)
            .dateOfJoining(new Date())
            .build();

    @Test
    @DisplayName("test should map employee to employeeEntity")
    void modelToEntity() {

        EmployeeEntity responseEntity = DtoConverter.modelToEntity(reqModel);

        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(responseEntity.getEmployeeId(), respEntity.getEmployeeId());
        Assertions.assertEquals(responseEntity.getEmail(), respEntity.getEmail());
        Assertions.assertEquals(responseEntity.getFirstName(), respEntity.getFirstName());
        Assertions.assertEquals(responseEntity.getLastName(), respEntity.getLastName());
        Assertions.assertEquals(String.valueOf(responseEntity.getDateOfJoining()), String.valueOf(respEntity.getDateOfJoining()));

    }

    @Test
    @DisplayName("test should map employeeEntity to employee")
    void EntityToModel() {

        Employee entity = DtoConverter.entityToModel(respEntity);

        Assertions.assertNotNull(entity);
        Assertions.assertEquals(entity.getEmployeeId(), reqModel.getEmployeeId());
        Assertions.assertEquals(entity.getEmail(), reqModel.getEmail());
        Assertions.assertEquals(entity.getFirstName(), reqModel.getFirstName());
        Assertions.assertEquals(entity.getLastName(), reqModel.getLastName());
        Assertions.assertEquals(String.valueOf(entity.getDateOfJoining()), String.valueOf(reqModel.getDateOfJoining()));

    }
}