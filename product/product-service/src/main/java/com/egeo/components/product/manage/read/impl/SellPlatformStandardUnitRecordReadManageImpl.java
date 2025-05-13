package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.SellPlatformStandardUnitRecordReadManage;
import com.egeo.components.product.dao.read.SellPlatformStandardUnitRecordReadDAO;
import com.egeo.components.product.po.SellPlatformStandardUnitRecordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class SellPlatformStandardUnitRecordReadManageImpl implements SellPlatformStandardUnitRecordReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SellPlatformStandardUnitRecordReadDAO sellPlatformStandardUnitRecordReadDAO;
	
	public SellPlatformStandardUnitRecordPO findSellPlatformStandardUnitRecordById(SellPlatformStandardUnitRecordPO po) {
		SellPlatformStandardUnitRecordPO sellPlatformStandardUnitRecordpo = new SellPlatformStandardUnitRecordPO();
		sellPlatformStandardUnitRecordpo.setId(po.getId());
		return sellPlatformStandardUnitRecordReadDAO.findById(sellPlatformStandardUnitRecordpo);
	}

	public PageResult<SellPlatformStandardUnitRecordPO> findSellPlatformStandardUnitRecordOfPage(SellPlatformStandardUnitRecordPO po, Pagination page) {
		
		PageResult<SellPlatformStandardUnitRecordPO> pageResult = new PageResult<SellPlatformStandardUnitRecordPO>();
		List<SellPlatformStandardUnitRecordPO> list = null;

		int cnt = sellPlatformStandardUnitRecordReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = sellPlatformStandardUnitRecordReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<SellPlatformStandardUnitRecordPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<SellPlatformStandardUnitRecordPO> findSellPlatformStandardUnitRecordAll(SellPlatformStandardUnitRecordPO po) {

		return sellPlatformStandardUnitRecordReadDAO.findAll(po,null);
	}
	
}
	