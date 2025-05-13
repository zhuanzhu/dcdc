package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.MerchantProdCauseReadManage;
import com.egeo.components.product.dao.read.MerchantProdCauseReadDAO;
import com.egeo.components.product.po.MerchantProdCausePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class MerchantProdCauseReadManageImpl implements MerchantProdCauseReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MerchantProdCauseReadDAO merchantProdCauseReadDAO;
	
	public MerchantProdCausePO findMerchantProdCauseById(MerchantProdCausePO po) {
		MerchantProdCausePO merchantProdCausepo = new MerchantProdCausePO();
		merchantProdCausepo.setId(po.getId());
		return merchantProdCauseReadDAO.findById(merchantProdCausepo);
	}

	public PageResult<MerchantProdCausePO> findMerchantProdCauseOfPage(MerchantProdCausePO po, Pagination page) {
		
		PageResult<MerchantProdCausePO> pageResult = new PageResult<MerchantProdCausePO>();
		List<MerchantProdCausePO> list = null;

		int cnt = merchantProdCauseReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = merchantProdCauseReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<MerchantProdCausePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<MerchantProdCausePO> findMerchantProdCauseAll(MerchantProdCausePO po) {

		return merchantProdCauseReadDAO.findAll(po,null);
	}
	
}
	