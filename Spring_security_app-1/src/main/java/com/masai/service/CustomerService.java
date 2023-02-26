package com.masai.service;

import java.util.List;

import com.masai.exception.CustomerException;
import com.masai.model.Customer;

public interface CustomerService {
 
	public Customer registerNewCustomer(Customer customer);
	
	public Customer getDetailsByEmail(String email) throws CustomerException;
	
	public List<Customer> getAllCustomer() throws CustomerException;
}
