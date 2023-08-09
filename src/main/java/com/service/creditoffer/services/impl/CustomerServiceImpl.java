package com.service.creditoffer.services.impl;

import com.service.creditoffer.entities.Customer;
import com.service.creditoffer.exceptions.BadRequestException;
import com.service.creditoffer.exceptions.NotFoundException;
import com.service.creditoffer.models.requests.CreateCustomerRequest;
import com.service.creditoffer.services.CustomerService;
import com.service.creditoffer.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author manoj.kumar
 */

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public String createCustomer(CreateCustomerRequest createCustomerRequest){
        Customer customer = new Customer(createCustomerRequest.getName(), createCustomerRequest.getAge());
        try {
            customerRepository.save(customer);
            return "Customer created successfully";
        } catch (Exception e){
            throw new BadRequestException("Error in creating customer : {}"+e.getMessage());
        }
    }

    public Customer getCustomerById(Long id){
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent())
            return customer.get();
        throw new NotFoundException("Customer not found with customerId: "+id);
    }
}
