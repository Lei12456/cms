package com.briup.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.demo.service.ICustomerService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.Message;
import com.briup.demo.utils.MessageUtil;
import com.briup.demo.utils.StatusCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description = "注册和登录接口")
public class LoginController {
	@Autowired
	private ICustomerService customerService;
	
	@GetMapping("/resiger")
	@ApiOperation("注册页面")
	public Message<String> resiger(String username,String password){
		try {
			customerService.saveCustomer(username, password);
			return MessageUtil.success();
		} catch (CustomerException e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误："+e.getMessage());
		}
     	
	}
	
	@GetMapping("/login")
	@ApiOperation("登录页面")
	public Message<String> login(String username,String password){
		try {
			if (customerService.selectCustomer(username, password)) {
				return MessageUtil.success("登录成功");	
			}else {
				return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "登录失败");
			}
		} catch (CustomerException e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "登录失败:"+e.getMessage());
		}
	}
	
	
	
	
	
	
	
	
}
