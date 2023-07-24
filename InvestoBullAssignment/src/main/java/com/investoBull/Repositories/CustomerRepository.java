package com.investoBull.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.investoBull.Models.Customer;



public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	
	public Optional<Customer> findByEmail(String email);
}
