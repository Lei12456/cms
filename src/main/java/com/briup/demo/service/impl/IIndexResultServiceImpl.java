package com.briup.demo.service.impl;
import java.util.ArrayList;
/**
 * 查询首页所有数据的实现类
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.bean.Article;
import com.briup.demo.bean.ArticleExample;
import com.briup.demo.bean.Category;
import com.briup.demo.bean.CategoryExample;
import com.briup.demo.bean.Link;
import com.briup.demo.bean.LinkExample;
import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.bean.ex.IndexResult;
import com.briup.demo.mapper.ArticleMapper;
import com.briup.demo.mapper.CategoryMapper;
import com.briup.demo.mapper.LinkMapper;
import com.briup.demo.mapper.ex.CategoryExMapper;
import com.briup.demo.service.ICategoryService;
import com.briup.demo.service.IIndexResultService;
import com.briup.demo.service.ILinkService;
import com.briup.demo.utils.CustomerException;

@Service
public class IIndexResultServiceImpl implements IIndexResultService {
	//关联文章的dao层接口
	@Autowired
	private ArticleMapper articleMapper;
	//关联超链接的service层接口
	@Autowired
	private ILinkService linkService;
	//关联栏目的service层接口
	@Autowired
	private ICategoryService categoryService;
	
	/**
	 * 自己：
	 */
	@Override
	public IndexResult findIndexResults() throws CustomerException{
		//查询所有栏目和其中的文章
		List<CategoryEx> categoryExs = new ArrayList<CategoryEx>();
		List<Category> categories = categoryService.findAllCategories();
		for (Category category : categories) {
			//遍历栏目集合查询出每个栏目下的文章 
			ArticleExample articleExample = new ArticleExample();
			articleExample.createCriteria().andCategoryIdEqualTo(category.getId());
		    List<Article> articles = articleMapper.selectByExample(articleExample);
		    CategoryEx categoryEx = new CategoryEx();
			categoryEx.setId(category.getId());
			categoryEx.setCode(category.getCode());
			categoryEx.setName(category.getName());	
			categoryEx.setArticles(articles);
		    categoryExs.add(categoryEx);
		}
		//查询所有链接
		List<Link> links = linkService.selectAllLink();
		IndexResult indexResult = new IndexResult();
		indexResult.setCategoryExs(categoryExs);
		indexResult.setLinks(links);
		return indexResult;
	}
/**
 * 老师上课代码
 */
	@Override
	public IndexResult findIndexResult() throws CustomerException {

		IndexResult indexResult = new IndexResult();
		indexResult.setCategoryExs(categoryService.findAllCategoryExs());
		indexResult.setLinks(linkService.selectAllLink());
		return indexResult;
	}

}
