package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.MerchantProdAttValueReadManage;
import com.egeo.components.product.dao.read.MerchantProdAttValueReadDAO;
import com.egeo.components.product.po.MerchantProdAttValuePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class MerchantProdAttValueReadManageImpl implements MerchantProdAttValueReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MerchantProdAttValueReadDAO merchantProdAttValueReadDAO;
	
	public MerchantProdAttValuePO findMerchantProdAttValueById(MerchantProdAttValuePO po) {
		MerchantProdAttValuePO merchantProdAttValuepo = new MerchantProdAttValuePO();
		merchantProdAttValuepo.setId(po.getId());
		return merchantProdAttValueReadDAO.findById(merchantProdAttValuepo);
	}

	public PageResult<MerchantProdAttValuePO> findMerchantProdAttValueOfPage(MerchantProdAttValuePO po, Pagination page) {
		
		PageResult<MerchantProdAttValuePO> pageResult = new PageResult<MerchantProdAttValuePO>();
		List<MerchantProdAttValuePO> list = null;

		int cnt = merchantProdAttValueReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = merchantProdAttValueReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<MerchantProdAttValuePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<MerchantProdAttValuePO> findMerchantProdAttValueAll(MerchantProdAttValuePO po) {

		return merchantProdAttValueReadDAO.findAll(po,null);
	}
	
}
	