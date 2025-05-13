package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.MerchantProductStoreReadManage;
import com.egeo.components.product.dao.read.MerchantProductStoreReadDAO;
import com.egeo.components.product.po.MerchantProductStorePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class MerchantProductStoreReadManageImpl implements MerchantProductStoreReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MerchantProductStoreReadDAO merchantProductStoreReadDAO;
	
	public MerchantProductStorePO findMerchantProductStoreById(MerchantProductStorePO po) {
		MerchantProductStorePO merchantProductStorepo = new MerchantProductStorePO();
		merchantProductStorepo.setId(po.getId());
		return merchantProductStoreReadDAO.findById(merchantProductStorepo);
	}

	public PageResult<MerchantProductStorePO> findMerchantProductStoreOfPage(MerchantProductStorePO po, Pagination page) {
		
		PageResult<MerchantProductStorePO> pageResult = new PageResult<MerchantProductStorePO>();
		List<MerchantProductStorePO> list = null;

		int cnt = merchantProductStoreReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = merchantProductStoreReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<MerchantProductStorePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<MerchantProductStorePO> findMerchantProductStoreAll(MerchantProductStorePO po) {

		return merchantProductStoreReadDAO.findAll(po,null);
	}
	
}
	