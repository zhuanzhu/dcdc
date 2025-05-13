package com.egeo.components.order.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.read.DeliveryCompanyReadManage;
import com.egeo.components.order.dao.read.DeliveryCompanyReadDAO;
import com.egeo.components.order.po.DeliveryCompanyPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class DeliveryCompanyReadManageImpl implements DeliveryCompanyReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DeliveryCompanyReadDAO deliveryCompanyReadDAO;
	
	public DeliveryCompanyPO findDeliveryCompanyById(DeliveryCompanyPO po) {
		DeliveryCompanyPO deliveryCompanypo = new DeliveryCompanyPO();
		deliveryCompanypo.setId(po.getId());
		return deliveryCompanyReadDAO.findById(deliveryCompanypo);
	}

	public PageResult<DeliveryCompanyPO> findDeliveryCompanyOfPage(DeliveryCompanyPO po, Pagination page) {
		
		PageResult<DeliveryCompanyPO> pageResult = new PageResult<DeliveryCompanyPO>();
		List<DeliveryCompanyPO> list = null;

		int cnt = deliveryCompanyReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = deliveryCompanyReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<DeliveryCompanyPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<DeliveryCompanyPO> findDeliveryCompanyAll(DeliveryCompanyPO po) {
		return deliveryCompanyReadDAO.findAll(po,null);
	}

	@Override
	public DeliveryCompanyPO queryDeliveryCompanyByName(String name) {
		return deliveryCompanyReadDAO.queryDeliveryCompanyByName(name);
	}
	
}
	