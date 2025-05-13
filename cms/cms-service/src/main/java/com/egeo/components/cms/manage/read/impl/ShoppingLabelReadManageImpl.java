package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.ShoppingLabelReadManage;
import com.egeo.components.cms.dao.read.ShoppingLabelReadDAO;
import com.egeo.components.cms.po.ShoppingLabelPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class ShoppingLabelReadManageImpl implements ShoppingLabelReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ShoppingLabelReadDAO shoppingLabelReadDAO;
	
	public ShoppingLabelPO findShoppingLabelById(ShoppingLabelPO po) {
		ShoppingLabelPO shoppingLabelpo = new ShoppingLabelPO();
		shoppingLabelpo.setId(po.getId());
		return shoppingLabelReadDAO.findById(shoppingLabelpo);
	}

	public PageResult<ShoppingLabelPO> findShoppingLabelOfPage(ShoppingLabelPO po, Pagination page) {
		
		PageResult<ShoppingLabelPO> pageResult = new PageResult<ShoppingLabelPO>();
		List<ShoppingLabelPO> list = null;

		int cnt = shoppingLabelReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = shoppingLabelReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<ShoppingLabelPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<ShoppingLabelPO> findShoppingLabelAll(ShoppingLabelPO po) {

		return shoppingLabelReadDAO.findAll(po,null);
	}

	@Override
	public List<ShoppingLabelPO> queryShoppingLabelListByGroupId(Long groupId) {
		return shoppingLabelReadDAO.queryShoppingLabelListByGroupId(groupId);
	}
	
}
	