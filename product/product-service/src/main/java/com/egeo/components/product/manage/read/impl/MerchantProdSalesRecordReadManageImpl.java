package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.MerchantProdSalesRecordReadManage;
import com.egeo.components.product.dao.read.MerchantProdSalesRecordReadDAO;
import com.egeo.components.product.po.MerchantProdSalesRecordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class MerchantProdSalesRecordReadManageImpl implements MerchantProdSalesRecordReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MerchantProdSalesRecordReadDAO merchantProdSalesRecordReadDAO;
	
	public MerchantProdSalesRecordPO findMerchantProdSalesRecordById(MerchantProdSalesRecordPO po) {
		MerchantProdSalesRecordPO merchantProdSalesRecordpo = new MerchantProdSalesRecordPO();
		merchantProdSalesRecordpo.setId(po.getId());
		return merchantProdSalesRecordReadDAO.findById(merchantProdSalesRecordpo);
	}

	public PageResult<MerchantProdSalesRecordPO> findMerchantProdSalesRecordOfPage(MerchantProdSalesRecordPO po, Pagination page) {
		
		PageResult<MerchantProdSalesRecordPO> pageResult = new PageResult<MerchantProdSalesRecordPO>();
		List<MerchantProdSalesRecordPO> list = null;

		int cnt = merchantProdSalesRecordReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = merchantProdSalesRecordReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<MerchantProdSalesRecordPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<MerchantProdSalesRecordPO> findMerchantProdSalesRecordAll(MerchantProdSalesRecordPO po) {

		return merchantProdSalesRecordReadDAO.findAll(po,null);
	}
	/**
	 * 根据suid查询su销售量
	 * @param standardUnitId
	 * @return
	 */
	@Override
	public Long findSalesRecordByStandardUnitId(Long standardUnitId) {
		// TODO Auto-generated method stub
		return merchantProdSalesRecordReadDAO.findSalesRecordByStandardUnitId(standardUnitId);
	}
	/**
	 * 根据puid查询pu销售信息
	 * @param puId
	 * @return
	 */
	@Override
	public MerchantProdSalesRecordPO findByPUId(Long puId) {
		// TODO Auto-generated method stub
		return merchantProdSalesRecordReadDAO.findByPUId(puId);
	}
	
}
	