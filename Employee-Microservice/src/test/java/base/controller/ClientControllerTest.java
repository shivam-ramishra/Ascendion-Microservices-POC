package base.controller;

import base.client.ClientConsumer;
import base.model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ClientControllerTest.class)
class ClientControllerTest {

    static final String BASE_URI = "/findClientByClientName/";

    @Mock
    private ClientConsumer clientConsumer;

    private MockMvc mockMvc;

    @InjectMocks
    private ClientController clientController;

    @BeforeEach
    void setup() {
        // Initialize MockMvc with the controller
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
    }

    @Test
    void findClientByClientName_success() throws Exception {

        final String URL = BASE_URI + "CLIENT-1";
        var client = Client.builder()
                .clientId(1)
                .clientName("CLIENT-1")
                .build();

        when(clientConsumer.findClientByClientName(any()))
                .thenReturn(client);

        mockMvc
                .perform(get(URL))
                .andExpect(status().isOk())
                .andDo(print());

        Mockito.verify(clientConsumer, Mockito.times(1)).findClientByClientName(any());
    }

    @Test
    void findClientByClientName_failed() {

        final String URL = BASE_URI + "CLIENT-1";
        when(clientConsumer.findClientByClientName(any()))
                .thenThrow(new RuntimeException("service not available"));

        try {
            mockMvc.perform(get(URL))
                    .andExpect(status().isInternalServerError());
        } catch (Exception e) {
            assert e.getCause() instanceof RuntimeException;
            assert "service not available".equals(e.getCause().getMessage());
        }

        Mockito.verify(clientConsumer, Mockito.times(1)).findClientByClientName(any());
    }

}
