package com.egeo.components.order.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.read.SoDeliveryItemReadManage;
import com.egeo.components.order.dao.read.SoDeliveryItemReadDAO;
import com.egeo.components.order.po.SoDeliveryItemPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class SoDeliveryItemReadManageImpl implements SoDeliveryItemReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SoDeliveryItemReadDAO soDeliveryItemReadDAO;
	
	public SoDeliveryItemPO findSoDeliveryItemById(SoDeliveryItemPO po) {
		SoDeliveryItemPO soDeliveryItempo = new SoDeliveryItemPO();
		soDeliveryItempo.setId(po.getId());
		return soDeliveryItemReadDAO.findById(soDeliveryItempo);
	}

	public PageResult<SoDeliveryItemPO> findSoDeliveryItemOfPage(SoDeliveryItemPO po, Pagination page) {
		
		PageResult<SoDeliveryItemPO> pageResult = new PageResult<SoDeliveryItemPO>();
		List<SoDeliveryItemPO> list = null;

		int cnt = soDeliveryItemReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = soDeliveryItemReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<SoDeliveryItemPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<SoDeliveryItemPO> findSoDeliveryItemAll(SoDeliveryItemPO po) {

		return soDeliveryItemReadDAO.findAll(po,null);
	}
	
}
	