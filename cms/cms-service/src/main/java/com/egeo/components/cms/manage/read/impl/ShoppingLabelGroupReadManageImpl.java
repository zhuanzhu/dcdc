package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.ShoppingLabelGroupReadManage;
import com.egeo.components.cms.dao.read.ShoppingLabelGroupReadDAO;
import com.egeo.components.cms.po.ShoppingLabelGroupPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class ShoppingLabelGroupReadManageImpl implements ShoppingLabelGroupReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ShoppingLabelGroupReadDAO shoppingLabelGroupReadDAO;
	
	public ShoppingLabelGroupPO findShoppingLabelGroupById(ShoppingLabelGroupPO po) {
		ShoppingLabelGroupPO shoppingLabelGrouppo = new ShoppingLabelGroupPO();
		shoppingLabelGrouppo.setId(po.getId());
		return shoppingLabelGroupReadDAO.findById(shoppingLabelGrouppo);
	}

	public PageResult<ShoppingLabelGroupPO> findShoppingLabelGroupOfPage(ShoppingLabelGroupPO po, Pagination page) {
		
		PageResult<ShoppingLabelGroupPO> pageResult = new PageResult<ShoppingLabelGroupPO>();
		List<ShoppingLabelGroupPO> list = null;

		int cnt = shoppingLabelGroupReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = shoppingLabelGroupReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<ShoppingLabelGroupPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<ShoppingLabelGroupPO> findShoppingLabelGroupAll(ShoppingLabelGroupPO po) {

		return shoppingLabelGroupReadDAO.findAll(po,null);
	}

	@Override
	public ShoppingLabelGroupPO queryShoppingLabelGroupByInstId(Long instId) {
		
		return shoppingLabelGroupReadDAO.queryShoppingLabelGroupByInstId(instId);
	}
	
}
	