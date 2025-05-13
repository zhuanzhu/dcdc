package com.egeo.components.pay.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.pay.dao.read.AwaitQueueReadDAO;
import com.egeo.components.pay.manage.read.AwaitQueueReadManage;
import com.egeo.components.pay.po.AwaitQueuePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class AwaitQueueReadManageImpl implements AwaitQueueReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AwaitQueueReadDAO awaitQueueReadDAO;
	
	public AwaitQueuePO findAwaitQueueById(AwaitQueuePO po) {
		AwaitQueuePO awaitQueuepo = new AwaitQueuePO();
		awaitQueuepo.setId(po.getId());
		return awaitQueueReadDAO.findById(awaitQueuepo);
	}

	public PageResult<AwaitQueuePO> findAwaitQueueOfPage(AwaitQueuePO po, Pagination page) {
		
		PageResult<AwaitQueuePO> pageResult = new PageResult<AwaitQueuePO>();
		List<AwaitQueuePO> list = null;

		int cnt = awaitQueueReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = awaitQueueReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<AwaitQueuePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<AwaitQueuePO> findAwaitQueueAll(AwaitQueuePO po) {

		return awaitQueueReadDAO.findAll(po,null);
	}
	/**
	 * 根据订单编号查询是否处于支付等待列表
	 * @param orderCode
	 * @return
	 */
	@Override
	public AwaitQueuePO findByOrderCode(String orderCode) {
		return awaitQueueReadDAO.findByOrderCode(orderCode);
	}

	@Override
	public AwaitQueuePO queryAwaitQueueByOrderId(Long orderId) {
		return awaitQueueReadDAO.queryAwaitQueueByOrderId(orderId);
	}
	
}
	