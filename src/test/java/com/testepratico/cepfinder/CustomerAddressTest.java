package com.testepratico.cepfinder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testepratico.cepfinder.models.CustomerAddressListModel;
import com.testepratico.cepfinder.repositories.CustomerAddressListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerAddressTest {
    private static final String API_PATH = "/customer";

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CustomerAddressListRepository repository;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void should_GetAddresses_ReturnList() throws Exception{
        CustomerAddressListModel model = new CustomerAddressListModel();
        model.setEmail("1@2.com");
        model.setCity("São Paulo");
        model.setNumber("n/a");
        model.setState("SP");
        model.setNeighborhood("Bom Retiro");
        List<CustomerAddressListModel> listModels = Arrays.asList(model);

        Mockito.when(repository.findAll(Mockito.any(Example.class)))
                .thenReturn(listModels);

        mockMvc.perform(get(API_PATH + "/address/1@2.com")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void should_SaveAddresses_Return201() throws Exception{
        CustomerAddressListModel model = new CustomerAddressListModel();
        model.setEmail("1@2.com");
        model.setStreet("R Pão de Açúcar");
        model.setCity("São Paulo");
        model.setNumber("n/a");
        model.setState("SP");
        model.setNeighborhood("Bom Retiro");

        Mockito.when(repository.save(Mockito.any(CustomerAddressListModel.class)))
                .thenReturn(model);

        String payload = asJsonString(model);

        mockMvc.perform(post(API_PATH + "/address")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}