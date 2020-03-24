package com.briup.demo.service.impl;
/**
 * 操作链接的Service 功能类
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.briup.demo.bean.Link;
import com.briup.demo.bean.LinkExample;
import com.briup.demo.bean.LinkExample.Criteria;
import com.briup.demo.mapper.LinkMapper;
import com.briup.demo.service.ILinkService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.StatusCodeUtil;

@Service
public class ILinkServiceImpl implements ILinkService {
	@Autowired
	private LinkMapper linkMapper;
	
	@Override
	public void saveOrupdateLink(Link link) throws CustomerException {
		/**
		 * 参数为引用类型，要做判空处理
		 */
		if(link == null) {
			throw new CustomerException(StatusCodeUtil.ERROR_CODE,"参数为空");
		}
		if (link.getId() == null) {
			linkMapper.insert(link);
		}
		else {
			linkMapper.updateByPrimaryKey(link);
		}
			
		
		
	}

	@Override
	public void deleteLinkById(Integer id) throws CustomerException {
		
		if(id == null) {
			throw new CustomerException(StatusCodeUtil.ERROR_CODE,"ID为空");
		}
		else {
			linkMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public List<Link> selectLinkByName(String name) throws CustomerException {
		name = name == null ? "":name.trim();
		LinkExample example = new LinkExample();
		if ("".equals(name)) {
			//如果搜所条件没写  则返回所有数据
			return linkMapper.selectByExample(example);
		}
		else {
			//如果搜所条件不为null  则返回指定数据
			example.createCriteria().andNameLike("%"+name+"%");
			
			return linkMapper.selectByExample(example);
		}
	}

		@Override
		public List<Link> selectAllLink() throws CustomerException {
				
				return linkMapper.selectByExample(new LinkExample());
		}

		@Override
		public void deleteAllLink() throws CustomerException {
				linkMapper.deleteByExample(new LinkExample());
				
		}


	
}
