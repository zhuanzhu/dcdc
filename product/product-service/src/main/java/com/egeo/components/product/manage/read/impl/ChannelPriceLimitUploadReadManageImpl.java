package com.egeo.components.product.manage.read.impl;

import com.egeo.components.product.dao.read.ChannelPriceLimitUploadReadDAO;
import com.egeo.components.product.dao.read.JdPriceLimitUploadReadDAO;
import com.egeo.components.product.manage.read.ChannelPriceLimitUploadReadManage;
import com.egeo.components.product.po.ChannelPriceLimitUploadPO;
import com.egeo.components.product.po.JdPriceLimitUploadPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ChannelPriceLimitUploadReadManageImpl implements ChannelPriceLimitUploadReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ChannelPriceLimitUploadReadDAO channelPriceLimitUploadReadDAO;

	@Override
	public ChannelPriceLimitUploadPO findChannelPriceLimitUploadById(ChannelPriceLimitUploadPO po) {
		ChannelPriceLimitUploadPO ChannelPriceLimitUploadpo = new ChannelPriceLimitUploadPO();
		ChannelPriceLimitUploadpo.setId(po.getId());
		return channelPriceLimitUploadReadDAO.findById(ChannelPriceLimitUploadpo);
	}

	@Override
	public PageResult<ChannelPriceLimitUploadPO> findChannelPriceLimitUploadOfPage(ChannelPriceLimitUploadPO po, Pagination page) {

		PageResult<ChannelPriceLimitUploadPO> pageResult = new PageResult<ChannelPriceLimitUploadPO>();
		List<ChannelPriceLimitUploadPO> list = null;

		int cnt = channelPriceLimitUploadReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = channelPriceLimitUploadReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	@Override
	public List<ChannelPriceLimitUploadPO> findChannelPriceLimitUploadAll(ChannelPriceLimitUploadPO po) {

		return channelPriceLimitUploadReadDAO.findAll(po,null);
	}

}
