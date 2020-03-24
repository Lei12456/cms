package com.briup.demo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 与栏目相关和 前端 交互
 * @author Administrator
 *
 */
import org.springframework.web.bind.annotation.RestController;

import com.briup.demo.bean.Article;
import com.briup.demo.bean.Category;
import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.service.ICategoryService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.Message;
import com.briup.demo.utils.MessageUtil;
import com.briup.demo.utils.StatusCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description =  "栏目相关接口")
public class CategoryController {
	
	@Autowired
	private ICategoryService iCategoryService;
	
	@PostMapping("/saveCategory")
	@ApiOperation("新增栏目")
	public Message<String> saveCategory(Category category) {
		try {
			iCategoryService.saveOrUpdateCategory(category);
			return MessageUtil.success();
		} catch (CustomerException e) {
			return MessageUtil.error(StatusCodeUtil.ERROR2_CODE, "系统错误"+e.getMessage());
		}
		
	}
	
	@PostMapping("/updateCategory")
	@ApiOperation("更新栏目")
	public Message<String> updateCategory(Category category) {
		try {
			iCategoryService.saveOrUpdateCategory(category);
			return MessageUtil.success();
		} catch (CustomerException e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误"+e.getMessage());
		}
		
	}
	
	
	
	@GetMapping("/findAllCategory")
	@ApiOperation("查询所有栏目")
	public Message<List<Category>> findAllCategory() {
		try {
			return MessageUtil.success(iCategoryService.findAllCategories());
		} catch (CustomerException e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误");
		}
		
	}
	@GetMapping("/deleteCategoryById")
	@ApiOperation("通过Id删除栏目")
	public Message<String> deleteCategoryById(int id) {
		try {
			iCategoryService.delelteCategoryById(id);
			return MessageUtil.success();
		} catch (CustomerException e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误");
		}
		
	}

	@GetMapping("/findCategoryById")
	@ApiOperation("通过id查询栏目")
	public Message<Category> findCategoryById(int id) {
		try {
			
			return MessageUtil.success(iCategoryService.findCategoryById(id));
		} catch (CustomerException e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误");
		}
		
	}
	@GetMapping("/deleteAllCategory")
	@ApiOperation("删除所有栏目")
	public Message<String> deleteAllCategory() {
		try {
			iCategoryService.deleteAllCategory();
			return MessageUtil.success();
		} catch (CustomerException e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误");
		}
		
	}
	
	@GetMapping("/getCategoryExById")
	@ApiOperation("根据栏目id查询栏目中所有文章")
	public Message<CategoryEx> getAriticles(Integer id) {
		try {
			return MessageUtil.success(iCategoryService.findCategoryExById(id));
		} catch (CustomerException e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误"+e.getMessage());
		}
		
	}
	
	
	
}
