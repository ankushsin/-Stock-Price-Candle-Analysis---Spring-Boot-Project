package com.investoBull.Services;
import java.util.List;

import com.investoBull.Exceptions.CustomerException;
import com.investoBull.Models.Customer;



public interface CustomerService {
	
	public Customer registerCustomer(Customer customer);
	
	public Customer getCustomerDetailsByEmail(String email)throws CustomerException;
	
	public List<Customer> getAllCustomerDetails()throws CustomerException;

}
