package com.service.creditoffer.controllers;

import com.service.creditoffer.models.requests.CreateCustomerRequest;
import com.service.creditoffer.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/{customer-id}")
    public ResponseEntity<?> getCustomerById(@PathVariable("customer-id") Long customerId){
        return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> createCustomer(@Valid @RequestBody CreateCustomerRequest createCustomerRequest){
        return new ResponseEntity<>(customerService.createCustomer(createCustomerRequest), HttpStatus.OK);
    }

}
