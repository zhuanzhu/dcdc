package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.MerchantProdClientReadManage;
import com.egeo.components.product.dao.read.MerchantProdClientReadDAO;
import com.egeo.components.product.po.MerchantProdClientPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class MerchantProdClientReadManageImpl implements MerchantProdClientReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MerchantProdClientReadDAO merchantProdClientReadDAO;
	
	public MerchantProdClientPO findMerchantProdClientById(MerchantProdClientPO po) {
		MerchantProdClientPO merchantProdClientpo = new MerchantProdClientPO();
		merchantProdClientpo.setId(po.getId());
		return merchantProdClientReadDAO.findById(merchantProdClientpo);
	}

	public PageResult<MerchantProdClientPO> findMerchantProdClientOfPage(MerchantProdClientPO po, Pagination page) {
		
		PageResult<MerchantProdClientPO> pageResult = new PageResult<MerchantProdClientPO>();
		List<MerchantProdClientPO> list = null;

		int cnt = merchantProdClientReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = merchantProdClientReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<MerchantProdClientPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<MerchantProdClientPO> findMerchantProdClientAll(MerchantProdClientPO po) {

		return merchantProdClientReadDAO.findAll(po,null);
	}
	
}
	