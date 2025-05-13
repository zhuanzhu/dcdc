package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.MerchantReadManage;
import com.egeo.components.product.dao.read.MerchantReadDAO;
import com.egeo.components.product.po.MerchantPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class MerchantReadManageImpl implements MerchantReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MerchantReadDAO merchantReadDAO;
	
	public MerchantPO findMerchantById(MerchantPO po) {
		MerchantPO merchantpo = new MerchantPO();
		merchantpo.setId(po.getId());
		return merchantReadDAO.findById(merchantpo);
	}

	public PageResult<MerchantPO> findMerchantOfPage(MerchantPO po, Pagination page) {
		
		PageResult<MerchantPO> pageResult = new PageResult<MerchantPO>();
		List<MerchantPO> list = null;

		int cnt = merchantReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = merchantReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<MerchantPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<MerchantPO> findMerchantAll(MerchantPO po) {

		return merchantReadDAO.findAll(po,null);
	}

	@Override
	public List<MerchantPO> findMerchantList() {
		return merchantReadDAO.findMerchantList();
	}

	@Override
	public List<MerchantPO> findMerchantListByType(Integer type) {
		return merchantReadDAO.findMerchantListByType(type);
	}

}
	