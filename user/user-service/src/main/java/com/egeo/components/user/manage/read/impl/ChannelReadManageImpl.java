package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.dao.read.ChannelReadDAO;
import com.egeo.components.user.manage.read.ChannelReadManage;
import com.egeo.components.user.po.ChannelPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class ChannelReadManageImpl implements ChannelReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ChannelReadDAO channelReadDAO;
	
	public ChannelPO findChannelById(ChannelPO po) {
		ChannelPO channelpo = new ChannelPO();
		channelpo.setId(po.getId());
		return channelReadDAO.findById(channelpo);
	}

	public PageResult<ChannelPO> findChannelOfPage(ChannelPO po, Pagination page) {
		
		PageResult<ChannelPO> pageResult = new PageResult<ChannelPO>();
		List<ChannelPO> list = null;

		int cnt = channelReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = channelReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<ChannelPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<ChannelPO> findChannelAll(ChannelPO po) {

		return channelReadDAO.findAll(po,null);
	}
	/**
	 * 根据版本类型：1、安卓 2、ios查询渠道信息
	 * @param vo
	 * @param req
	 * @return
	 */
	@Override
	public List<ChannelPO> findChannelByType(int type) {
		ChannelPO channelPO = new ChannelPO();
		channelPO.setType(type);
		return channelReadDAO.findAll(channelPO,null);
	}
	
}
	