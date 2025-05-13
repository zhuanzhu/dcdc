package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.SkuAttValueReadManage;
import com.egeo.components.product.dao.read.SkuAttValueReadDAO;
import com.egeo.components.product.po.SkuAttValuePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class SkuAttValueReadManageImpl implements SkuAttValueReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SkuAttValueReadDAO skuAttValueReadDAO;
	
	public SkuAttValuePO findSkuAttValueById(SkuAttValuePO po) {
		SkuAttValuePO skuAttValuepo = new SkuAttValuePO();
		skuAttValuepo.setId(po.getId());
		return skuAttValueReadDAO.findById(skuAttValuepo);
	}

	public PageResult<SkuAttValuePO> findSkuAttValueOfPage(SkuAttValuePO po, Pagination page) {
		
		PageResult<SkuAttValuePO> pageResult = new PageResult<SkuAttValuePO>();
		List<SkuAttValuePO> list = null;

		int cnt = skuAttValueReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = skuAttValueReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<SkuAttValuePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<SkuAttValuePO> findSkuAttValueAll(SkuAttValuePO po) {

		return skuAttValueReadDAO.findAll(po,null);
	}
	
}
	