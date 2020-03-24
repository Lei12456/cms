package com.briup.demo.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.bean.Article;
import com.briup.demo.bean.ArticleExample;
import com.briup.demo.bean.Category;
import com.briup.demo.bean.CategoryExample;
import com.briup.demo.mapper.ArticleMapper;
import com.briup.demo.mapper.CategoryMapper;
import com.briup.demo.service.IArticleService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.StatusCodeUtil;

import ch.qos.logback.core.status.StatusUtil;

@Service
public class IArticleServiceImpl implements IArticleService {
	@Autowired
	private ArticleMapper articleMapper;
	@Autowired
	private CategoryMapper categoryMapper;
	
	@Override
	public void saveOrUpdateAriticle(Article article) throws CustomerException {
			if (article == null) {
				throw new CustomerException(StatusCodeUtil.ERROR_CODE,"参数为空");
			}
			if (article.getId() == null) {
				//要额外添加数据
				article.setPublishdate(new Date());
				article.setClicktimes(0);	
				articleMapper.insert(article);		
			}
			else {
				article.setPublishdate(new Date());
				articleMapper.updateByPrimaryKey(article);
			}
	}

	@Override
	public void deleteAriticleById(int id) throws CustomerException {
		articleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Article> findArticleByCondithion(String key, String condition) throws CustomerException {	
		/*
		 *1，没有添加任何条件，写查询所有文章
		 *2，没有指定栏目，但指定了关键字，则根据关键字查询文章
		 *3，指定栏目，没有指定关键字，则查询栏目中的所有文章
		 *4，指定栏目和关键字，则根据栏目和关键字查询文章
		 */
		key  = key == null ? "" : key.trim();
		condition = condition == null? "" :condition.trim();
		ArticleExample example = new ArticleExample();
		
		if ("".equals(key) && "".equals(condition)) {
			//情况一：
			return articleMapper.selectByExample(example);
		}else if (!"".equals(key) && "".equals(condition)) {
			//情况二：
			example.createCriteria().andTitleLike("%"+key+"%");
			return articleMapper.selectByExample(example);
		}else if (!"".equals(condition) && "".equals(key)) {
			//情况三：
			//根据condition查询出数据库中符合condition的栏目
			CategoryExample categoryExample = new CategoryExample();
			categoryExample.createCriteria().andNameEqualTo(condition);
			List<Category> list = categoryMapper.selectByExample(categoryExample);
			if(list.size() > 0) {
				//再通过栏目的id级联查询出栏目中的文章
				example.createCriteria().andCategoryIdEqualTo(list.get(0).getId());
				
			}else {
				throw new CustomerException(StatusCodeUtil.ERROR_CODE,"没有指定栏目");
			}
			 return articleMapper.selectByExample(example);
		}else {
			//情况四：
			CategoryExample categoryExample = new CategoryExample();
			categoryExample.createCriteria().andNameEqualTo(condition);
			List<Category> list = categoryMapper.selectByExample(categoryExample);
			
			if (list.size() > 0) {
				//and拼接方式
				example.createCriteria().andCategoryIdEqualTo(list.get(0).getId())
				.andTitleLike("%"+key+"%");
				//or拼接方式
				//example.or().andTitleLike("%"+key+"%");
			    return articleMapper.selectByExample(example);
			}
			else {
				throw new CustomerException(StatusCodeUtil.ERROR_CODE,"没有指定栏目");
			}
			
		}
		
	}
	@Override
	public Article findArticleById(Integer id) throws CustomerException {
		Article article = articleMapper.selectByPrimaryKey(id);
		//判空处理
		Integer clicktimes = article.getClicktimes() == null ? 0:article.getClicktimes();
		article.setClicktimes(clicktimes+1);
		this.saveOrUpdateAriticle(article);
		return article;
	}

}
