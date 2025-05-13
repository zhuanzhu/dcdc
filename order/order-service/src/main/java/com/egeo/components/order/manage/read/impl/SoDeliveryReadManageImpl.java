package com.egeo.components.order.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.read.SoDeliveryReadManage;
import com.egeo.components.order.dao.read.SoDeliveryReadDAO;
import com.egeo.components.order.po.SoDeliveryPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class SoDeliveryReadManageImpl implements SoDeliveryReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SoDeliveryReadDAO soDeliveryReadDAO;
	
	public SoDeliveryPO findSoDeliveryById(SoDeliveryPO po) {
		SoDeliveryPO soDeliverypo = new SoDeliveryPO();
		soDeliverypo.setId(po.getId());
		return soDeliveryReadDAO.findById(soDeliverypo);
	}

	public PageResult<SoDeliveryPO> findSoDeliveryOfPage(SoDeliveryPO po, Pagination page) {
		
		PageResult<SoDeliveryPO> pageResult = new PageResult<SoDeliveryPO>();
		List<SoDeliveryPO> list = null;

		int cnt = soDeliveryReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = soDeliveryReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<SoDeliveryPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<SoDeliveryPO> findSoDeliveryAll(SoDeliveryPO po) {

		return soDeliveryReadDAO.findAll(po,null);
	}
	
}
	