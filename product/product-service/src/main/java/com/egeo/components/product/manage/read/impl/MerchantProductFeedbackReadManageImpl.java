package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.MerchantProductFeedbackReadManage;
import com.egeo.components.product.dao.read.MerchantProductFeedbackReadDAO;
import com.egeo.components.product.po.MerchantProductFeedbackPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class MerchantProductFeedbackReadManageImpl implements MerchantProductFeedbackReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MerchantProductFeedbackReadDAO merchantProductFeedbackReadDAO;
	
	public MerchantProductFeedbackPO findMerchantProductFeedbackById(MerchantProductFeedbackPO po) {
		MerchantProductFeedbackPO merchantProductFeedbackpo = new MerchantProductFeedbackPO();
		merchantProductFeedbackpo.setId(po.getId());
		return merchantProductFeedbackReadDAO.findById(merchantProductFeedbackpo);
	}

	public PageResult<MerchantProductFeedbackPO> findMerchantProductFeedbackOfPage(MerchantProductFeedbackPO po, Pagination page) {
		
		PageResult<MerchantProductFeedbackPO> pageResult = new PageResult<MerchantProductFeedbackPO>();
		List<MerchantProductFeedbackPO> list = null;

		int cnt = merchantProductFeedbackReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = merchantProductFeedbackReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<MerchantProductFeedbackPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<MerchantProductFeedbackPO> findMerchantProductFeedbackAll(MerchantProductFeedbackPO po) {

		return merchantProductFeedbackReadDAO.findAll(po,null);
	}
	
}
	