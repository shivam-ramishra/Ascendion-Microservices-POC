package base.service;


import base.client.ClientConsumer;
import base.dto.EmployeeEntity;
import base.exception.ClientNotFoundException;
import base.exception.EmployeeNotFoundException;
import base.exception.InvalidInputException;
import base.model.Client;
import base.model.Employee;
import base.repo.EmployeeRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepo repo;

    @Mock
    private ClientConsumer clientConsumer;

    @InjectMocks
    private EmployeeServiceImpl service;

    @Test
    void saveOrUpdate_success() {
        var reqModel = Employee.builder()
                .firstName("FNAME")
                .lastName("LNAME")
                .clientName("CLIENT-1")
                .email("demo@email.com")
                .contact("123456789")
                .salary(123.4)
                .department("SYS")
                .isActive(true)
                .dateOfJoining(new Date())
                .build();


        var respEntity = EmployeeEntity.builder()
                .employeeId(101L)
                .firstName("FNAME")
                .lastName("LNAME")
                .clientName("CLIENT-1")
                .email("demo@email.com")
                .contact("123456789")
                .salary(123.4)
                .department("SYS")
                .isActive(true)
                .dateOfJoining(new Date())
                .build();

        var respModel = Employee.builder()
                .employeeId(101L)
                .firstName("FNAME")
                .lastName("LNAME")
                .clientName("CLIENT-1")
                .email("demo@email.com")
                .contact("123456789")
                .salary(123.4)
                .department("SYS")
                .isActive(true)
                .dateOfJoining(new Date())
                .build();

        when(clientConsumer.findClientByClientName(any(String.class)))
                .thenReturn(Client.builder().clientId(101).clientName("CLIENT-1").build());
        when(repo.save(any(EmployeeEntity.class)))
                .thenReturn(respEntity);

        Employee savedEmp = service.addOrUpdateEmployee(reqModel);

        assertNotNull(savedEmp);
        assertEquals(respModel.getEmployeeId(), savedEmp.getEmployeeId());

    }

    @Test
    void saveOrUpdate_inputNullFailure() {
        assertThrows(InvalidInputException.class,
                () -> service.addOrUpdateEmployee(null));
    }

    @Test
    void saveOrUpdate_inputClientIdNullFailure() {
        var reqModel = Employee.builder()
                .firstName("FNAME")
                .lastName("LNAME")
                .email("demo.email.com")
                .contact("123456789")
                .salary(123.4)
                .department("SYS")
                .isActive(true)
                .dateOfJoining(new Date())
                .build();

        assertThrows(InvalidInputException.class,
                () -> service.addOrUpdateEmployee(reqModel));

    }

    @Test
    void saveOrUpdate_clientConsumerReturnsNull() {
        var reqModel = Employee.builder()
                .firstName("FNAME")
                .lastName("LNAME")
                .clientName("CLIENT-1")
                .email("demo@email.com")
                .contact("123456789")
                .salary(123.4)
                .department("SYS")
                .isActive(true)
                .dateOfJoining(new Date())
                .build();

        when(clientConsumer.findClientByClientName(any(String.class)))
                .thenReturn(null);

        assertThrows(ClientNotFoundException.class,
                () -> service.addOrUpdateEmployee(reqModel));
    }

    @Test
    void saveOrUpdate_ClientNameNotFound() {
        var reqModel = Employee.builder()
                .firstName("FNAME")
                .lastName("LNAME")
                .clientName("CLIENT-1")
                .email("demo@email.com")
                .contact("123456789")
                .salary(123.4)
                .department("SYS")
                .isActive(true)
                .dateOfJoining(new Date())
                .build();

        when(clientConsumer.findClientByClientName(any(String.class)))
                .thenThrow(new RuntimeException("Client not found."));

        assertThrows(ClientNotFoundException.class,
                () -> service.addOrUpdateEmployee(reqModel));
    }

    @Test
    void addOrUpdate_somethingWentWrong() {
        var reqModel = Employee.builder()
                .firstName("FNAME")
                .lastName("LNAME")
                .clientName("CLIENT-1")
                .email("demo@email.com")
                .contact("123456789")
                .salary(123.4)
                .department("SYS")
                .isActive(true)
                .dateOfJoining(new Date())
                .build();

        when(clientConsumer.findClientByClientName(any(String.class)))
                .thenReturn(Client.builder().clientId(101).clientName("CLIENT-1").build());
        when(repo.save(any(EmployeeEntity.class)))
                .thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class, () -> service.addOrUpdateEmployee(reqModel));
    }
//
//    @Test
//    void addOrUpdate_ClientNotFound() {
//        var reqModel = Employee.builder()
//                .firstName("FNAME")
//                .lastName("LNAME")
//                .clientName("CLIENT-1")
//                .email("demo@email.com")
//                .contact("123456789")
//                .salary(123.4)
//                .department("SYS")
//                .isActive(true)
//                .dateOfJoining(new Date())
//                .build();
//
//        when(clientConsumer.findClientByClientName("CLIENT-1"))
//                .thenThrow(ClientNotFoundException.class);
//
//        assertThrows(ClientNotFoundException.class, () -> service.addOrUpdateEmployee(reqModel));
//    }


    @Test
    void deleteEmployee_success() {
        var respEntity = EmployeeEntity.builder()
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

        when(repo.findById(101L))
                .thenReturn(ofNullable(respEntity));
        doNothing().when(repo).deleteById(any());
        boolean isDeleted = service.deleteEmployee(101L);
        assertTrue(isDeleted);
    }

    @Test
    void deleteEmployee_empNotFound_failure() {
        when(repo.findById(101L))
                .thenReturn(empty());
        assertThrows(EmployeeNotFoundException.class, () -> service.deleteEmployee(101));
    }

    @Test
    void deleteEmployee_deleteFailure() {
        var respEntity = EmployeeEntity.builder()
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

        when(repo.findById(101L))
                .thenReturn(ofNullable(respEntity));
        doThrow(new RuntimeException()).when(repo).deleteById(any());

        boolean isDeleted = service.deleteEmployee(101L);
        assertFalse(isDeleted);
    }

    @Test
    void findAll_success() {
        var respModel1 = EmployeeEntity.builder()
                .employeeId(101L)
                .firstName("FNAME")
                .lastName("LNAME")
                .email("demo@email.com")
                .contact("9876543210")
                .salary(123.4)
                .department("SYS")
                .isActive(true)
                .dateOfJoining(new Date())
                .build();
        var respModel2 = EmployeeEntity.builder()
                .employeeId(102L)
                .firstName("FNAME2")
                .lastName("LNAME2")
                .email("demo2@email.com")
                .contact("1234567890")
                .salary(123.4)
                .department("SYS")
                .isActive(true)
                .dateOfJoining(new Date())
                .build();
        var respModel3 = EmployeeEntity.builder()
                .employeeId(103L)
                .firstName("FNAME3")
                .lastName("LNAME3")
                .email("demo3@email.com")
                .contact("123543210")
                .salary(123.4)
                .department("SYS")
                .isActive(true)
                .dateOfJoining(new Date())
                .build();

        List<EmployeeEntity> mockList = List.of(respModel1, respModel2, respModel3);
        when(repo.findAll())
                .thenReturn(mockList);

        List<Employee> all = service.findAll();

        assertFalse(all.isEmpty());
        assertEquals(3, all.size());
        assertEquals(101L, all.get(0).getEmployeeId());
    }

    @Test
    void findAll_failure() {
        when(repo.findAll())
                .thenReturn(emptyList());

        List<Employee> all = service.findAll();
        assertTrue(all.isEmpty());

    }

    @Test
    void findEmployeeById_success() {
        var entity = EmployeeEntity.builder()
                .employeeId(101L)
                .firstName("FNAME")
                .lastName("LNAME")
                .email("demo@email.com")
                .contact("9876543210")
                .salary(123.4)
                .department("SYS")
                .isActive(true)
                .dateOfJoining(new Date())
                .build();

        when(repo.findById(101L))
                .thenReturn(ofNullable(entity));

        Employee emp = service.findEmployeeById(101L);

        assertEquals(101L, emp.getEmployeeId());
    }

    @Test
    void findEmployeeById_failure() {
        when(repo.findById(101L))
                .thenReturn(empty());

        assertThrows(EmployeeNotFoundException.class,
                () -> service.findEmployeeById(101L));
    }

    @Test
    void findEmployeeByClientId_success() {
        var entity = EmployeeEntity.builder()
                .employeeId(101L)
                .clientName("ABC_123")
                .firstName("FNAME")
                .lastName("LNAME")
                .email("demo@email.com")
                .contact("9876543210")
                .salary(123.4)
                .department("SYS")
                .isActive(true)
                .dateOfJoining(new Date())
                .build();

        when(repo.findByClientName("ABC_123"))
                .thenReturn(List.of(entity));

        var emp = service.findByClientName("ABC_123");

        assertEquals(1, emp.size());
        assertEquals("FNAME", emp.get(0).getFirstName());
    }

    @Test
    void findEmployeeByClientId_failure() {
        when(repo.findByClientName(any()))
                .thenThrow(ClientNotFoundException.class);

        assertThrows(ClientNotFoundException.class,
                () -> service.findByClientName("ABC_123"));

    }

    @Test
    void findAll_somethingWentWrong() {
        when(repo.findAll())
                .thenThrow(new RuntimeException("Find all failed"));

        assertThrows(RuntimeException.class, () -> service.findAll());
    }

}





