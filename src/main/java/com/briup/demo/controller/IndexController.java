package com.briup.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.demo.bean.ex.IndexResult;
import com.briup.demo.service.IIndexResultService;
import com.briup.demo.utils.Message;
import com.briup.demo.utils.MessageUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 首页页面
 * 
 */

@RestController
@Api(description = "index页面")
public class IndexController {
	@Autowired
	private IIndexResultService indexResultService;
	
	@GetMapping("/index")
	@ApiOperation("所有栏目和对应文章信息")
	public Message<IndexResult> index(){
		return MessageUtil.success(indexResultService.findIndexResults());
	}
	@GetMapping("/getIndex")
	@ApiOperation("所有栏目和对应文章信息")
	public Message<IndexResult> getindex(){
		return MessageUtil.success(indexResultService.findIndexResult());
	}
	
	
	
	
}
