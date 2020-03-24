package com.briup.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.briup.demo.bean.Customer;

public interface CustomerDao  extends JpaRepository<Customer, Integer>{
	
     List<Customer> findByUsernameAndPassword(String username,String password);
     List<Customer> findByUsername(String username);
}
