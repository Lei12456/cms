package com.briup.demo.service;
/**
 * 首页页面的Serve层接口
 * @author Administrator
 *
 */

import com.briup.demo.bean.ex.IndexResult;
import com.briup.demo.utils.CustomerException;

public interface IIndexResultService {
	/**
	 * 查询首页所有信息:自己
	 */
	IndexResult findIndexResults() throws CustomerException;
	/**
	 * 老师这块功能的实现
	 */
	IndexResult findIndexResult() throws CustomerException;
}
