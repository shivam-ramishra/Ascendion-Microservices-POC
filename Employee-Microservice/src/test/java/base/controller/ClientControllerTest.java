package base.controller;

import base.client.ClientConsumer;
import base.model.Client;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ClientControllerTest.class)
class ClientControllerTest {

    static final String BASE_URI = "/findClientByClientName/";

    @Mock
    private ClientConsumer clientConsumer;

    @Autowired
    private MockMvc mockMvc;

    @Disabled
    @Test
    void findClientByClientName_success() throws Exception {

        final String URL = BASE_URI + "abc";
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
    }



}
