package com.testepratico.cepfinder.controllers;

import com.testepratico.cepfinder.services.zipcode.ZipCodeResponse;
import com.testepratico.cepfinder.services.zipcode.ZipCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/zipcode")
public class ZipCodeController {

    @Autowired
    private ZipCodeService zipCodeService;

    @GetMapping("/query")
    public ResponseEntity<ZipCodeResponse> getAddress(String zipCode){
        ZipCodeResponse response = zipCodeService.getAddressByZipCode(zipCode);
        if (response.getCep() == null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }
        return ResponseEntity.ok(response);
    }
}