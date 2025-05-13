package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.MerchantProdAttNameReadManage;
import com.egeo.components.product.dao.read.MerchantProdAttNameReadDAO;
import com.egeo.components.product.po.MerchantProdAttNamePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class MerchantProdAttNameReadManageImpl implements MerchantProdAttNameReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MerchantProdAttNameReadDAO merchantProdAttNameReadDAO;
	
	public MerchantProdAttNamePO findMerchantProdAttNameById(MerchantProdAttNamePO po) {
		MerchantProdAttNamePO merchantProdAttNamepo = new MerchantProdAttNamePO();
		merchantProdAttNamepo.setId(po.getId());
		return merchantProdAttNameReadDAO.findById(merchantProdAttNamepo);
	}

	public PageResult<MerchantProdAttNamePO> findMerchantProdAttNameOfPage(MerchantProdAttNamePO po, Pagination page) {
		
		PageResult<MerchantProdAttNamePO> pageResult = new PageResult<MerchantProdAttNamePO>();
		List<MerchantProdAttNamePO> list = null;

		int cnt = merchantProdAttNameReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = merchantProdAttNameReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<MerchantProdAttNamePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<MerchantProdAttNamePO> findMerchantProdAttNameAll(MerchantProdAttNamePO po) {

		return merchantProdAttNameReadDAO.findAll(po,null);
	}
	
}
	