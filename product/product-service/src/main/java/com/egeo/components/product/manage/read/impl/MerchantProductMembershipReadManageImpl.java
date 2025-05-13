package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.MerchantProductMembershipReadManage;
import com.egeo.components.product.dao.read.MerchantProductMembershipReadDAO;
import com.egeo.components.product.po.MerchantProductMembershipPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class MerchantProductMembershipReadManageImpl implements MerchantProductMembershipReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MerchantProductMembershipReadDAO merchantProductMembershipReadDAO;
	
	public MerchantProductMembershipPO findMerchantProductMembershipById(MerchantProductMembershipPO po) {
		MerchantProductMembershipPO merchantProductMembershippo = new MerchantProductMembershipPO();
		merchantProductMembershippo.setId(po.getId());
		return merchantProductMembershipReadDAO.findById(merchantProductMembershippo);
	}

	public PageResult<MerchantProductMembershipPO> findMerchantProductMembershipOfPage(MerchantProductMembershipPO po, Pagination page) {
		
		PageResult<MerchantProductMembershipPO> pageResult = new PageResult<MerchantProductMembershipPO>();
		List<MerchantProductMembershipPO> list = null;

		int cnt = merchantProductMembershipReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = merchantProductMembershipReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<MerchantProductMembershipPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<MerchantProductMembershipPO> findMerchantProductMembershipAll(MerchantProductMembershipPO po) {

		return merchantProductMembershipReadDAO.findAll(po,null);
	}
	
}
	