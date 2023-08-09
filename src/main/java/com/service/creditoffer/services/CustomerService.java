package com.service.creditoffer.services;

import com.service.creditoffer.entities.Customer;
import com.service.creditoffer.models.requests.CreateCustomerRequest;

public interface CustomerService {
    Customer getCustomerById(Long id);
    String createCustomer(CreateCustomerRequest createCustomerRequest);
}
