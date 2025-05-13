package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.condition.ChannelActivityCondition;
import com.egeo.components.user.dao.read.ChannelActivityReadDAO;
import com.egeo.components.user.manage.read.ChannelActivityReadManage;
import com.egeo.components.user.po.ChannelActivityPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class ChannelActivityReadManageImpl implements ChannelActivityReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ChannelActivityReadDAO channelActivityReadDAO;
	
	public ChannelActivityPO findChannelActivityById(ChannelActivityPO po) {
		ChannelActivityPO channelActivitypo = new ChannelActivityPO();
		channelActivitypo.setId(po.getId());
		return channelActivityReadDAO.findById(channelActivitypo);
	}

	public PageResult<ChannelActivityCondition> findChannelActivityOfPage(ChannelActivityCondition po, Pagination page) {
		
		PageResult<ChannelActivityCondition> pageResult = new PageResult<ChannelActivityCondition>();
		List<ChannelActivityCondition> list = null;

		int cnt = channelActivityReadDAO.countChannelActivityConditionOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = channelActivityReadDAO.findChannelActivityConditionOfPage(po, page);
		} else {
			list = new ArrayList<ChannelActivityCondition>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<ChannelActivityPO> findChannelActivityAll(ChannelActivityPO po) {

		return channelActivityReadDAO.findAll(po,null);
	}

	@Override
	public ChannelActivityPO findByShortCode(String shortCode) {
		return channelActivityReadDAO.findByShortCode(shortCode);
	}
	
}
	