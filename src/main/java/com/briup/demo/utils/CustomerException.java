package com.briup.demo.utils;

import org.omg.IOP.ExceptionDetailMessage;

/**
  * 自定义异常
 * @author Administrator
 *
 */
public class CustomerException extends RuntimeException{
	private static final long serialVersionUID = 1l ;
	
	private Integer code;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
	public CustomerException() {
		super();
	}

	public CustomerException(Integer code,String message) {
		super(message);
		this.code = code;
	}

	@Override
	public String toString() {
		return "CustomerException [code=" + code + "]";
	}
	
	
	
}
