package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.SkuAttNameReadManage;
import com.egeo.components.product.condition.SkuAttNameCondition;
import com.egeo.components.product.dao.read.SkuAttNameReadDAO;
import com.egeo.components.product.po.SkuAttNamePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class SkuAttNameReadManageImpl implements SkuAttNameReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SkuAttNameReadDAO skuAttNameReadDAO;
	
	public SkuAttNamePO findSkuAttNameById(SkuAttNamePO po) {
		SkuAttNamePO skuAttNamepo = new SkuAttNamePO();
		skuAttNamepo.setId(po.getId());
		return skuAttNameReadDAO.findById(skuAttNamepo);
	}

	public PageResult<SkuAttNamePO> findSkuAttNameOfPage(SkuAttNamePO po, Pagination page) {
		
		PageResult<SkuAttNamePO> pageResult = new PageResult<SkuAttNamePO>();
		List<SkuAttNamePO> list = null;

		int cnt = skuAttNameReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = skuAttNameReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<SkuAttNamePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<SkuAttNamePO> findSkuAttNameAll(SkuAttNamePO po) {

		return skuAttNameReadDAO.findAll(po,null);
	}
	/**
	 * 根据skuid查询sku属性和属性值集合
	 * @param skuId
	 * @return
	 */
	@Override
	public List<SkuAttNameCondition> findSkuAttNameByskuId(Long skuId) {
		// TODO Auto-generated method stub
		return skuAttNameReadDAO.findSkuAttNameByskuId(skuId);
	}

	@Override
	public Long findLastId() {
		return skuAttNameReadDAO.findLastId();
	}

}
	