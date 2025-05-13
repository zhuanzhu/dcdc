package com.egeo.components.order.manage.read.impl;

import com.egeo.components.order.dao.read.ChannelAddressReadDAO;
import com.egeo.components.order.manage.read.ChannelAddressReadManage;
import com.egeo.components.order.po.ChannelAddressPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ChannelAddressReadManageImpl implements ChannelAddressReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ChannelAddressReadDAO addressReadDAO;

	@Override
	public ChannelAddressPO findChannelAddressById(ChannelAddressPO po) {
		ChannelAddressPO addresspo = new ChannelAddressPO();
		addresspo.setId(po.getId());
		return addressReadDAO.findById(addresspo);
	}

	@Override
	public PageResult<ChannelAddressPO> findChannelAddressOfPage(ChannelAddressPO po, Pagination page) {

		PageResult<ChannelAddressPO> pageResult = new PageResult<>();
		List<ChannelAddressPO> list = null;

		int cnt = addressReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = addressReadDAO.findOfPage(po, page);
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
	public List<ChannelAddressPO> findChannelAddressAll(ChannelAddressPO po) {

		return addressReadDAO.findAll(po,null);
	}

	@Override
	public ChannelAddressPO findByReceiverAddressIdChannel(Long receiverAddressId,String channelCode){
		if(EmptyUtil.isEmpty(receiverAddressId) || EmptyUtil.isEmpty(channelCode)){
			return null;
		}
		return addressReadDAO.findByReceiverAddressIdChannel(receiverAddressId,channelCode);
	}

}
