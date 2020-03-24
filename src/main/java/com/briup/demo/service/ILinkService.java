package com.briup.demo.service;

import java.util.List;

import com.briup.demo.bean.Link;
import com.briup.demo.bean.LinkExample;
import com.briup.demo.utils.CustomerException;

/**
 * 关于链接的相关操作
 * @author Administrator
 *
 */
public interface ILinkService {
	/**
	 * 保存或者更新链接信息
	 * @param link
	 */
	void saveOrupdateLink(Link link) throws CustomerException;
	/**
	 *通过Id删除链接讯息
	 */
	void deleteLinkById(Integer id) throws CustomerException;
	/**
	 * 删除所有链接
	 */
	
	void deleteAllLink() throws CustomerException;
	/**
	 * 通过链接名查询链接信息
	 */
	List<Link> selectLinkByName(String name) throws CustomerException;
	/**
	 * 查询所有链接信息
	 */
	List<Link> selectAllLink() throws CustomerException;
	

	
	
}	
