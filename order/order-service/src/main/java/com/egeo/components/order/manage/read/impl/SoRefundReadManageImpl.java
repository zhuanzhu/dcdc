package com.egeo.components.order.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.read.SoRefundReadManage;
import com.egeo.components.order.dao.read.SoRefundReadDAO;
import com.egeo.components.order.po.SoRefundPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class SoRefundReadManageImpl implements SoRefundReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SoRefundReadDAO soRefundReadDAO;

	public SoRefundPO findSoRefundById(SoRefundPO po) {
		SoRefundPO soRefundpo = new SoRefundPO();
		soRefundpo.setId(po.getId());
		return soRefundReadDAO.findById(soRefundpo);
	}

	public PageResult<SoRefundPO> findSoRefundOfPage(SoRefundPO po, List<Long> userIdList, Pagination page) {

		PageResult<SoRefundPO> pageResult = new PageResult<SoRefundPO>();
		List<SoRefundPO> list = null;

		int cnt = soRefundReadDAO.countOfPageByBlurry(po, userIdList);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = soRefundReadDAO.findOfPageByBlurry(po, userIdList, page);
		} else {
			list = new ArrayList<SoRefundPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<SoRefundPO> findSoRefundAll(SoRefundPO po) {

		return soRefundReadDAO.findAll(po,null);
	}


	@Override
	public List<SoRefundPO> getByBatchJIDianId(Long batchId,Long orderId){
		SoRefundPO po = new SoRefundPO();
		po.setJidianAccountBatchId(batchId);
		po.setSoId(orderId);
		return findSoRefundAll(po);
	}

	@Override
	public List<SoRefundPO> getByBatchFuBiId(Long batchId,Long orderId){
		SoRefundPO po = new SoRefundPO();
		po.setFubiAccountBatchId(batchId);
		po.setSoId(orderId);
		return findSoRefundAll(po);
	}

	@Override
	public List<SoRefundPO> getByBatchCashId(Long batchId,Long orderId){
		SoRefundPO po = new SoRefundPO();
		po.setCashAccountBatchId(batchId);
		po.setSoId(orderId);
		return findSoRefundAll(po);
	}

}
