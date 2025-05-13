package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.MerchantProductCompanyReadManage;
import com.egeo.components.product.dao.read.MerchantProductCompanyReadDAO;
import com.egeo.components.product.po.MerchantProductCompanyPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class MerchantProductCompanyReadManageImpl implements MerchantProductCompanyReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MerchantProductCompanyReadDAO merchantProductCompanyReadDAO;
	
	public MerchantProductCompanyPO findMerchantProductCompanyById(MerchantProductCompanyPO po) {
		MerchantProductCompanyPO merchantProductCompanypo = new MerchantProductCompanyPO();
		merchantProductCompanypo.setId(po.getId());
		return merchantProductCompanyReadDAO.findById(merchantProductCompanypo);
	}

	public PageResult<MerchantProductCompanyPO> findMerchantProductCompanyOfPage(MerchantProductCompanyPO po, Pagination page) {
		
		PageResult<MerchantProductCompanyPO> pageResult = new PageResult<MerchantProductCompanyPO>();
		List<MerchantProductCompanyPO> list = null;

		int cnt = merchantProductCompanyReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = merchantProductCompanyReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<MerchantProductCompanyPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<MerchantProductCompanyPO> findMerchantProductCompanyAll(MerchantProductCompanyPO po) {

		return merchantProductCompanyReadDAO.findAll(po,null);
	}
	
}
	