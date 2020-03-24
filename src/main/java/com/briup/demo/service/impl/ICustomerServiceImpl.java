package com.briup.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.bean.Customer;
import com.briup.demo.dao.CustomerDao;
import com.briup.demo.service.ICustomerService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.StatusCodeUtil;
/**
 * 注册和登录功能的实现类
 * @author Administrator
 *
 */
@Service
public class ICustomerServiceImpl implements ICustomerService {
	@Autowired
	private CustomerDao customerDao;
	
	@Override
	public void saveCustomer(String username,String password) throws CustomerException {
		username = username == null ? "":username.trim();
		password = password == null ? "":password.trim();
		List<Customer> list = customerDao.findByUsername(username);
		if ( list != null && list.size() > 0) {
			throw new CustomerException(StatusCodeUtil.ERROR_CODE,"用户名已存在");
		}
		else {
			Customer customer = new Customer();
			customer.setUsername(username);
			customer.setPassword(password);
			customerDao.save(customer);
		}
		
	}

	@Override
	public boolean selectCustomer(String username, String password) throws CustomerException {
		List<Customer> list = customerDao.findByUsernameAndPassword(username, password);
		if (list != null && list.size() == 0) {
			throw new CustomerException(StatusCodeUtil.ERROR_CODE,"用户名或密码错误");
		}
		else {
			return true;
		}
	
	}

}
