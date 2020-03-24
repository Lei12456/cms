package com.briup.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.demo.bean.Link;
import com.briup.demo.bean.LinkExample;
import com.briup.demo.service.ILinkService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.Message;
import com.briup.demo.utils.MessageUtil;
import com.briup.demo.utils.StatusCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 与链接相关和 前端 交互
 * @author Administrator
 *
 */
@RestController
@Api(description = "链接相关接口")
public class LinkController {

	@Autowired
	private ILinkService  linkService;
	
	
	@PostMapping("/addLink")
	@ApiOperation("新增链接")
	public Message<String> addLink(Link link){
		try {
			linkService.saveOrupdateLink(link);
			return MessageUtil.success();
		} catch (CustomerException e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误异常");
		}	
	}
	
	@PostMapping("/updLink")
	@ApiOperation("修改链接")
	public Message<String> updLink(Link link){
			linkService.saveOrupdateLink(link);
			return MessageUtil.success();
		
	}
	
	@GetMapping("/delLinkById")
	@ApiOperation("通过ID删除链接")
	public Message<String> delLink(Integer id){
		try {
			linkService.deleteLinkById(id);
			return MessageUtil.success();
		} catch (CustomerException e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误异常");
		}
	}
	
	
	@GetMapping("/selLinkByName")
	@ApiOperation("通过连接名模糊查询")
	public Message<List<Link>> selLinkByName(String name){
		try {
			
			return MessageUtil.success(linkService.selectLinkByName(name));
		} catch (CustomerException e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误异常");
		}
	}
	@GetMapping("/delAllLink")
	@ApiOperation("删除所有链接")
	public Message<String> delAllLink(){
		try {
			linkService.deleteAllLink();
			return MessageUtil.success();
		} catch (CustomerException e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误异常");
		}
	}
	@GetMapping("/selAllLink")
	@ApiOperation("查询所有链接")
	public Message<List<Link>> selAllLink(){
		try {
			List<Link> list = linkService.selectAllLink();
			return MessageUtil.success(list);
		} catch (CustomerException e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误异常");
		}
	}

	

}
