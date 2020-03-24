package com.briup.demo.service;

import java.util.List;

import com.briup.demo.bean.Article;
import com.briup.demo.bean.Category;
import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.utils.CustomerException;

/**
 * 栏目相关的Service层
 * @author Administrator
 *
 */
public interface ICategoryService {
	/**
	 * 添加或修改栏目信息
	 */
	public void saveOrUpdateCategory(Category category) throws CustomerException;
	
	/**
	 * 查询所有栏目信息
	 */
	public List<Category> findAllCategories()throws CustomerException;
	
	/**
	 *根据id删除栏目信息
	 */
	public void delelteCategoryById(int id) throws CustomerException;
	/**
	 * 删除所有栏目信息
	 */
	public void deleteAllCategory() throws CustomerException;
	/**
	 * 根据id查找栏目信息
	 */
	public Category findCategoryById(int id) throws CustomerException;
	/**
	 * 查询栏目信息并级联查询包含的文章信息
	 */
	public List<CategoryEx> findAllCategoryExs() throws  CustomerException;
	/**
	 * 查询每个栏目所有文章
	 */
	public CategoryEx findCategoryExById(Integer id) throws CustomerException;
	
	
	
	
	
}
