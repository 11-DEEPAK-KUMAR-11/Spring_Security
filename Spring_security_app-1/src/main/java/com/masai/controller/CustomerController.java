package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.CustomerException;
import com.masai.model.Customer;
import com.masai.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService cservice;
	
	@Autowired
	private PasswordEncoder pEncode;
	
	@GetMapping("/hello")
	public String welcome()
	{
		return "Welcome to spring security";
	}
	
	@PostMapping("/customers")
	public ResponseEntity<Customer> saveCustomerHandler(@RequestBody Customer customer)
	{
		customer.setPassword(pEncode.encode(customer.getPassword()));
		
		Customer newCustomer=cservice.registerNewCustomer(customer);
		
		return new ResponseEntity<>(newCustomer,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/customers/{email}")
	public ResponseEntity<Customer> findCustomerByEmail(@PathVariable("email") String email) throws CustomerException
	{
		Customer found=cservice.getDetailsByEmail(email);
		return new ResponseEntity<>(found,HttpStatus.OK);
	}
	
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> findCustomersList() throws CustomerException
	{
		List<Customer> all=cservice.getAllCustomer();
		
		return new ResponseEntity<>(all,HttpStatus.OK);
	}
	
	
	
	
	
	
	
}
