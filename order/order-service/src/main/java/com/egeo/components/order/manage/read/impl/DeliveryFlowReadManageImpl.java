package com.egeo.components.order.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.read.DeliveryFlowReadManage;
import com.egeo.components.order.dao.read.DeliveryFlowReadDAO;
import com.egeo.components.order.po.DeliveryFlowPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class DeliveryFlowReadManageImpl implements DeliveryFlowReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DeliveryFlowReadDAO deliveryFlowReadDAO;
	
	public DeliveryFlowPO findDeliveryFlowById(DeliveryFlowPO po) {
		DeliveryFlowPO deliveryFlowpo = new DeliveryFlowPO();
		deliveryFlowpo.setId(po.getId());
		return deliveryFlowReadDAO.findById(deliveryFlowpo);
	}

	public PageResult<DeliveryFlowPO> findDeliveryFlowOfPage(DeliveryFlowPO po, Pagination page) {
		
		PageResult<DeliveryFlowPO> pageResult = new PageResult<DeliveryFlowPO>();
		List<DeliveryFlowPO> list = null;

		int cnt = deliveryFlowReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = deliveryFlowReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<DeliveryFlowPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<DeliveryFlowPO> findDeliveryFlowAll(DeliveryFlowPO po) {

		return deliveryFlowReadDAO.findAll(po,null);
	}
	
}
	