package com.testepratico.cepfinder.controllers;

import com.testepratico.cepfinder.dtos.CustomerAddressDto;
import com.testepratico.cepfinder.models.CustomerAddressListModel;
import com.testepratico.cepfinder.repositories.CustomerAddressListRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/customer")
public class CustomerAddressController {

    @Autowired
    CustomerAddressListRepository customerAddressListRepository;

    @PostMapping("/address")
    public ResponseEntity<CustomerAddressListModel> saveAddress(@RequestBody @Valid CustomerAddressDto addressListDto){
        var addressModel = new CustomerAddressListModel();
        BeanUtils.copyProperties(addressListDto, addressModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerAddressListRepository.save(addressModel));
    }

    @GetMapping("/address/{email}")
    public ResponseEntity<List<CustomerAddressListModel>> getAddressesByEmail(@PathVariable(value="email") @Valid String customerEmail){
        return ResponseEntity.status(HttpStatus.OK).body(customerAddressListRepository.findByEmail(customerEmail));
    }
}