package com.egeo.components.order.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.read.FrozenPuReadManage;
import com.egeo.components.order.dao.read.FrozenPuReadDAO;
import com.egeo.components.order.po.FrozenPuPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class FrozenPuReadManageImpl implements FrozenPuReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private FrozenPuReadDAO frozenPuReadDAO;
	
	public FrozenPuPO findFrozenPuById(FrozenPuPO po) {
		FrozenPuPO frozenPupo = new FrozenPuPO();
		frozenPupo.setId(po.getId());
		return frozenPuReadDAO.findById(frozenPupo);
	}

	public PageResult<FrozenPuPO> findFrozenPuOfPage(FrozenPuPO po, Pagination page) {
		
		PageResult<FrozenPuPO> pageResult = new PageResult<FrozenPuPO>();
		List<FrozenPuPO> list = null;

		int cnt = frozenPuReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = frozenPuReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<FrozenPuPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<FrozenPuPO> findFrozenPuAll(FrozenPuPO po) {

		return frozenPuReadDAO.findAll(po,null);
	}
	
}
	