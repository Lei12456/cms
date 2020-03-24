package com.briup.demo.service.impl;
/**
 * 操作栏目的相关Service类
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.bean.Article;
import com.briup.demo.bean.ArticleExample;
import com.briup.demo.bean.Category;
import com.briup.demo.bean.CategoryExample;
import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.mapper.ArticleMapper;
import com.briup.demo.mapper.CategoryMapper;
import com.briup.demo.mapper.ex.CategoryExMapper;
import com.briup.demo.service.ICategoryService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.StatusCodeUtil;

@Service
public class ICategoryServiceImpl implements ICategoryService {
	
	@Autowired
	private CategoryMapper categoryMapper;
	@Autowired
	private ArticleMapper articleMapper;
	@Autowired
	private CategoryExMapper categoryExMapper;

	@Override
	public void saveOrUpdateCategory(Category category) throws CustomerException {
		if (category == null) {
			throw new CustomerException(StatusCodeUtil.ERROR_CODE,"参数为空");
		}
		
		CategoryExample example = new CategoryExample();
		example.createCriteria().andNameEqualTo(category.getName()).andCodeEqualTo(category.getCode());
		List<Category> list = categoryMapper.selectByExample(example);
		System.out.println(list);
		if (category.getId() == null) {
			if (list != null && list.size() == 0) {
				categoryMapper.insert(category);
			}
			else {
				throw new CustomerException(StatusCodeUtil.ERROR2_CODE,"栏目名已存在");
			}
		}
		else {
			categoryMapper.updateByPrimaryKey(category);
		}
		
	}
	
	@Override
	public List<Category> findAllCategories() throws CustomerException {
		List<Category> list = categoryMapper.selectByExample(new CategoryExample());
		return list;
		
	}

	@Override
	public void delelteCategoryById(int id) throws CustomerException {
		//要删除栏目，首先先删除栏目中的文章
		ArticleExample example = new ArticleExample();
		example.createCriteria().andCategoryIdEqualTo(id);
		
		articleMapper.deleteByExample(example);
		categoryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Category findCategoryById(int id) throws CustomerException {
		
		return categoryMapper.selectByPrimaryKey(id);
	}

	@Override
	public void deleteAllCategory() throws CustomerException{
		categoryMapper.deleteByExample(new CategoryExample());
	}

	@Override
	public List<CategoryEx> findAllCategoryExs() throws CustomerException {
		
		return categoryExMapper.findAllCategoryExs();
	}
	/**
	 * 通过id查询栏目扩展类的所有文章
	 */
	@Override
	public CategoryEx findCategoryExById(Integer id) throws CustomerException {
		
		return categoryExMapper.findCategoryExById(id);
	}

}
