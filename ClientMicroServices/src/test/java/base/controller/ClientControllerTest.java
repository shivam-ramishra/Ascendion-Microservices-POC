package base.controller;

import base.exception.ClientNotFoundException;
import base.exception.NoClientsFoundException;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ClientController.class)
class ClientControllerTest {

    private static final String BASE_URI = "/api/client/";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ClientService service;

    @Test
    void addOrUpdate_success() throws Exception {
        Client reqModel = Client.builder()
                .clientName("CLIENT-1")
                .build();
        Client respModel = Client.builder()
                .clientId(1)
                .clientName("CLIENT-1")
                .build();

        when(service.addOrUpdateClient(reqModel))
                .thenReturn(respModel);

        mockMvc
                .perform(post(BASE_URI + "addOrUpdate")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(reqModel)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.clientId", Is.is(1)))
                .andExpect(jsonPath("$.clientName", Is.is("CLIENT-1")))
                .andDo(print());
    }

    @Test
    void addOrUpdate_failure() throws Exception {
        Client reqModel = Client.builder()
                .clientName("CLIENT-1")
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
        final String URL = BASE_URI + "delete/1";
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
                .clientId(1)
                .clientName("CLIENT-1")
                .build();
        Client cli2 = Client.builder()
                .clientId(2)
                .clientName("CLIENT-2")
                .build();

        when(service.getClients())
                .thenReturn(List.of(cli1, cli2));

        mockMvc
                .perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].clientId", Is.is(1)))
                .andExpect(jsonPath("$[1].clientId", Is.is(2)))
                .andExpect(jsonPath("$[0].clientName", Is.is("CLIENT-1")))
                .andExpect(jsonPath("$[1].clientName", Is.is("CLIENT-2")))
                .andDo(print());
    }

    @Test
    void findAll_failure() throws Exception {
        final String URL = BASE_URI + "findAll";

        when(service.getClients()).thenThrow(new NoClientsFoundException(""));

        mockMvc
                .perform(get(URL))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    void findClientByName_success() throws Exception {
        final String URL = BASE_URI + "client/name";

        Client client = Client.builder()
                .clientId(1)
                .clientName("name")
                .build();

        when(service.findClientByName("name"))
                .thenReturn(client);

        mockMvc
                .perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clientId", Is.is(1)))
                .andExpect(jsonPath("$.clientName", Is.is("name")))
                .andDo(print());
    }

    @Test
    void findClientByName_failure() throws Exception {
        final String URL = BASE_URI + "client/name";

        when(service.findClientByName("name")).thenThrow(ClientNotFoundException.class);

        mockMvc
                .perform(get(URL))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }
}
