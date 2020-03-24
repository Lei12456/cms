package com.briup.demo.service;

import java.util.List;

import com.briup.demo.bean.Article;
import com.briup.demo.utils.CustomerException;

/**
 *	文章内容的Service的相关接口 
 */
public interface IArticleService {
	/**
	 * 新增或修改文章
	 * @param 
	 */
	public void saveOrUpdateAriticle(Article article) throws CustomerException;
	/**
	 * 删除文章
	 */
	public void deleteAriticleById(int id) throws CustomerException;
	/**
	 * 查询文章
	 * @param key 表示搜索框
	 * @param condition 表示栏目框
	 */
	public List<Article> findArticleByCondithion(String key,String condition) throws CustomerException;
	/**
	 * 根据Id查询文章
	 */
	public Article findArticleById(Integer id) throws CustomerException;
	
}
