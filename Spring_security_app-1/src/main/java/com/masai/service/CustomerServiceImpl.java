package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.masai.exception.CustomerException;
import com.masai.model.Customer;
import com.masai.repository.CustomerRepository;

public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerRepository crepo;
	
	@Override
	public Customer registerNewCustomer(Customer customer) {
		
		return crepo.save(customer);
	}

	@Override
	public Customer getDetailsByEmail(String email) throws CustomerException {
		
		return crepo.findByEmail(email).orElseThrow( () -> new CustomerException("Customer not found with this email"));
	}

	@Override
	public List<Customer> getAllCustomer() throws CustomerException {

        List<Customer> all=crepo.findAll();
        
        if(all.isEmpty())
        	throw new CustomerException("Customer not found");
        
		return all;
	}

}
