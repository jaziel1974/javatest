package com.testepratico.cepfinder.services.zipcode;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ZipCodeService {
    private RestTemplate restTemplate = new RestTemplate();
    @Value("${zipcodeservice.baseurl}")
    private String cepBaseUrl;

    public ZipCodeResponse getAddressByZipCode(String zipCode){
        return restTemplate.getForObject(cepBaseUrl + "/"+zipCode+"/json", ZipCodeResponse.class);
    }
}