package com.briup.demo.service;

import com.briup.demo.utils.CustomerException;

/**
 * 用户的service层
 * @author Administrator
 *
 */
public interface ICustomerService {
	/**
	 * 注册用户
	 */
	void saveCustomer(String username,String password) throws CustomerException;
	/**
	 * 登录验证
	 */
	boolean selectCustomer(String username,String password) throws CustomerException;
}
