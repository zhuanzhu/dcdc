package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.SellPlatformMerchantProdReadManage;
import com.egeo.components.product.condition.SellPlatformMerchantProdCondition;
import com.egeo.components.product.dao.read.SellPlatformMerchantProdReadDAO;
import com.egeo.components.product.po.SellPlatformMerchantProdPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class SellPlatformMerchantProdReadManageImpl implements SellPlatformMerchantProdReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SellPlatformMerchantProdReadDAO sellPlatformMerchantProdReadDAO;
	
	public SellPlatformMerchantProdPO findSellPlatformMerchantProdById(SellPlatformMerchantProdPO po) {
		SellPlatformMerchantProdPO sellPlatformMerchantProdpo = new SellPlatformMerchantProdPO();
		sellPlatformMerchantProdpo.setId(po.getId());
		return sellPlatformMerchantProdReadDAO.findById(sellPlatformMerchantProdpo);
	}

	public PageResult<SellPlatformMerchantProdPO> findSellPlatformMerchantProdOfPage(SellPlatformMerchantProdPO po, Pagination page) {
		
		PageResult<SellPlatformMerchantProdPO> pageResult = new PageResult<SellPlatformMerchantProdPO>();
		List<SellPlatformMerchantProdPO> list = null;

		int cnt = sellPlatformMerchantProdReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = sellPlatformMerchantProdReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<SellPlatformMerchantProdPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<SellPlatformMerchantProdPO> findSellPlatformMerchantProdAll(SellPlatformMerchantProdPO po) {

		return sellPlatformMerchantProdReadDAO.findAll(po,null);
	}
	/**
	 * 根据su草稿id查询su草稿比价平台信息
	 * @param sellPlatformMerchantProdDTO
	 * @return
	 */
	@Override
	public List<SellPlatformMerchantProdCondition> findByMerchantProdId(SellPlatformMerchantProdPO po) {
		// TODO Auto-generated method stub
		return sellPlatformMerchantProdReadDAO.findByMerchantProdId(po,null);
	}
	
}
	