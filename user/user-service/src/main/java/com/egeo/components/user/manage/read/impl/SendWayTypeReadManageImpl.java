package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.read.SendWayTypeReadManage;
import com.egeo.components.user.dao.read.SendWayTypeReadDAO;
import com.egeo.components.user.po.SendWayTypePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class SendWayTypeReadManageImpl implements SendWayTypeReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SendWayTypeReadDAO sendWayTypeReadDAO;
	
	public SendWayTypePO findSendWayTypeById(SendWayTypePO po) {
		SendWayTypePO sendWayTypepo = new SendWayTypePO();
		sendWayTypepo.setId(po.getId());
		return sendWayTypeReadDAO.findById(sendWayTypepo);
	}

	public PageResult<SendWayTypePO> findSendWayTypeOfPage(SendWayTypePO po, Pagination page) {
		
		PageResult<SendWayTypePO> pageResult = new PageResult<SendWayTypePO>();
		List<SendWayTypePO> list = null;

		int cnt = sendWayTypeReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = sendWayTypeReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<SendWayTypePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<SendWayTypePO> findSendWayTypeAll(SendWayTypePO po) {

		return sendWayTypeReadDAO.findAll(po,null);
	}

	@Override
	public List<String> findSendWayTypeByInfoId(Long infoId) {
		return sendWayTypeReadDAO.findSendWayTypeByInfoId(infoId);
	}
	
}
	