package base.controller;

import base.exception.EmployeeNotFoundException;
import base.model.Employee;
import base.repo.EmployeeRepo;
import base.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    static final String BASE_URI = "/api/employee/";


    @Autowired
    MockMvc mockMvc;

    @MockBean
    private EmployeeService service;

    @MockBean
    private EmployeeRepo repo;

    @Test
    void addOrUpdate_success() throws Exception {
        Employee reqModel = Employee.builder()
                .firstName("FIRST")
                .lastName("LAST")
                .email("abc@email.com")
                .contact("1234567890")
                .salary(123.4)
                .department("IT")
                .build();
        Employee respModel = Employee.builder()
                .employeeId(101L)
                .firstName("FIRST")
                .lastName("LAST")
                .email("abc@email.com")
                .contact("1234567890")
                .salary(123.4)
                .department("IT")
                .build();

        when(service.addOrUpdateEmployee(reqModel))
                .thenReturn(respModel);

        mockMvc
                .perform(
                        post(BASE_URI + "addOrUpdate")
                                .contentType("application/json")
                                .content(new ObjectMapper().writeValueAsString(reqModel)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.employeeId", Is.is(101)))
                .andExpect(jsonPath("$.lastName", Is.is("LAST")))
                .andDo(print());

    }

    @Test
    void addOrUpdate_failure() throws Exception {
        Employee reqModel = Employee.builder()
                .firstName("FIRST")
                .lastName("LAST")
                .email("abc@email.com")
                .contact("1234567890")
                .salary(123.4)
                .department("IT")
                .build();

        when(service.addOrUpdateEmployee(reqModel))
                .thenReturn(null);

        mockMvc
                .perform(
                        post("/api/employee/addOrUpdate")
                                .contentType("application/json")
                                .content(new ObjectMapper().writeValueAsString(reqModel)))
                .andExpect(status().isBadRequest())
                .andDo(print());

    }

    @Test
    void deleteEmp_success() throws Exception {
        final String URL = BASE_URI + "delete/101";

        when(service.deleteEmployee(101L))
                .thenReturn(true);

        mockMvc
                .perform(delete(URL))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void deleteEmp_failure() throws Exception {
        final String URL = BASE_URI + "delete/101";

        when(service.deleteEmployee(101L))
                .thenReturn(false);

        mockMvc
                .perform(delete(URL))
                .andExpect(status().isInternalServerError())
                .andDo(print());
    }


    @Test
    void findAll_success() throws Exception {
        final String URL = BASE_URI + "findAll";

        Employee emp1 = Employee.builder()
                .employeeId(101L)
                .firstName("EMP1")
                .lastName("1")
                .email("emp1@email.com")
                .contact("1234567890")
                .salary(123.4)
                .department("IT")
                .build();
        Employee emp2 = Employee.builder()
                .employeeId(102L)
                .firstName("EMP2")
                .lastName("2")
                .email("emp2@email.com")
                .contact("1234567890")
                .salary(123.4)
                .department("IT")
                .build();

        when(service.findAll())
                .thenReturn(List.of(emp1, emp2));

        mockMvc
                .perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].employeeId", Is.is(101)))
                .andExpect(jsonPath("$[1].employeeId", Is.is(102)))
                .andExpect(jsonPath("$[0].firstName", Is.is("EMP1")))
                .andExpect(jsonPath("$[1].firstName", Is.is("EMP2")))
                .andDo(print());

    }

    @Test
    void findAll_failure() throws Exception {
        final String URL = BASE_URI + "findAll";

        when(service.findAll()).thenThrow(new RuntimeException(""));
        mockMvc
                .perform(get(URL))
                .andExpect(status().isInternalServerError())
                .andDo(print());
    }

    @Test
    void findEmployeeById_success() throws Exception {
        final String URL = BASE_URI + "id/102";

        Employee emp = Employee.builder()
                .employeeId(102L)
                .firstName("EMP2")
                .lastName("2")
                .email("emp2@email.com")
                .contact("1234567890")
                .salary(123.4)
                .department("IT")
                .build();

        when(service.findEmployeeById(102L))
                .thenReturn(emp);
        mockMvc
                .perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeId", Is.is(102)))
                .andExpect(jsonPath("$.firstName", Is.is("EMP2")))
                .andDo(print());
    }

    @Test
    void findEmployeeById_failure() throws Exception {
        final String URL = BASE_URI + "id/102";

        when(service.findEmployeeById(102L))
                .thenThrow(EmployeeNotFoundException.class);
        mockMvc
                .perform(get(URL))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}
