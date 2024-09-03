package base.controller;

import base.exception.ClientNotFoundException;
import base.model.Client;
import base.service.ClientService;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    static final String BASE_URI = "/api/client";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ClientService service;

    @Test
    void addOrUpdate_success() throws Exception{
        Client reqModel = Client.builder()
                .firstName("FIRST")
                .lastName("LAST")
                .build();
        Client respModel = Client.builder()
                .id(1)
                .firstName("FIRST")
                .lastName("LAST")
                .build();

        when(service.addOrUpdateClient(reqModel))
                .thenReturn(respModel);

        mockMvc
                .perform( post(BASE_URI + "addOrUpdate")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(reqModel)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", Is.is(1)))
                .andExpect(jsonPath("$.lastName", Is.is("LAST")))
                .andDo(print());
    }

    @Test
    void addOrUpdate_failure() throws Exception{
        Client reqModel = Client.builder()
                .firstName("FIRST")
                .lastName("LAST")
                .build();

        when(service.addOrUpdateClient(reqModel))
                .thenReturn(null);

        mockMvc
                .perform(post(BASE_URI + "addOrUpdate")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(reqModel)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void deleteEmp_success() throws Exception {
        final String URL = BASE_URI +"delete/1";
        when(service.deleteClient(1))
                .thenReturn(true);

        mockMvc
                .perform(delete(URL))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void deleteEmp_failure() throws Exception {
        final String URL = BASE_URI + "delete/1";
        when(service.deleteClient(1))
                .thenReturn(false);

        mockMvc
                .perform(delete(URL))
                .andExpect(status().isInternalServerError())
                .andDo(print());
    }

    @Test
    void findAll_success() throws Exception {
        final String URL = BASE_URI + "findAll";

        Client cli1 = Client.builder()
                .id(1)
                .firstName("CLIENT1")
                .lastName("LAST1")
                .build();
        Client cli2 = Client.builder()
                .id(2)
                .firstName("CLIENT2")
                .lastName("LAST2")
                .build();

        when(service.getClients())
                .thenReturn(List.of(cli1, cli2));

        mockMvc
                .perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", Is.is(1)))
                .andExpect(jsonPath("$[1].id", Is.is(2)))
                .andExpect(jsonPath("$[0].firstName", Is.is("CLIENT1")))
                .andExpect(jsonPath("$[1].firstName", Is.is("CLIENT2")))
                .andDo(print());
    }

    @Test
    void findAll_failure() throws Exception {
        final String URL = BASE_URI + "findAll";

        when(service.getClients()).thenThrow(new RuntimeException(""));
        mockMvc
                .perform(get(URL))
                .andExpect(status().isInternalServerError())
                .andDo(print());
    }
}
