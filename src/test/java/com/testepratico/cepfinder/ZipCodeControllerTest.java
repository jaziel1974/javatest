package com.testepratico.cepfinder;

import com.fasterxml.jackson.databind.ObjectMapper;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.testepratico.cepfinder.services.zipcode.ZipCodeResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ZipCodeControllerTest {
    private static final String API_PATH = "/zipcode";

    @Autowired
    private MockMvc mockMvc;

    @RegisterExtension
    static WireMockExtension wireMockServer = WireMockExtension.newInstance()
            .options(WireMockConfiguration.wireMockConfig().dynamicPort())
            .build();

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("zipcodeservice.baseurl", wireMockServer::baseUrl);
    }

    @Test
    void GetAddresses_ReturnOne() throws Exception {
        String zipCode = "06913300";
        ZipCodeResponse zipCodeResponse = new ZipCodeResponse();
        zipCodeResponse.setCep(zipCode);

        wireMockServer.stubFor(WireMock.get(urlMatching("/.*"))
                .willReturn(
                        com.github.tomakehurst.wiremock.client.WireMock.aResponse()
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                .withBody(asJsonString(zipCodeResponse))
                ));

        mockMvc.perform(get(API_PATH + "/query?zipCode="+zipCode+"/json")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.cep").value(zipCode))
                        .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void GetAddress_NotFound() throws Exception{
        String zipCode = "06913999";
        ZipCodeResponse zipCodeResponse = new ZipCodeResponse();
        zipCodeResponse.setCep(null);

        wireMockServer.stubFor(WireMock.get(urlMatching("/.*"))
                .willReturn(
                        com.github.tomakehurst.wiremock.client.WireMock.aResponse()
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                .withBody(asJsonString(zipCodeResponse))
                                ));

        mockMvc.perform(get(API_PATH + "/query?zipCode="+zipCode+"/json")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isNoContent())
                        .andDo(MockMvcResultHandlers.print());
        ;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}