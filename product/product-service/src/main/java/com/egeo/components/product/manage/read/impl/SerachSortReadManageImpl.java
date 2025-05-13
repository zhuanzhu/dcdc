package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.SerachSortReadManage;
import com.egeo.components.product.dao.read.SerachSortReadDAO;
import com.egeo.components.product.po.SerachSortPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class SerachSortReadManageImpl implements SerachSortReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SerachSortReadDAO serachSortReadDAO;
	
	public SerachSortPO findSerachSortById(SerachSortPO po) {
		SerachSortPO serachSortpo = new SerachSortPO();
		serachSortpo.setId(po.getId());
		return serachSortReadDAO.findById(serachSortpo);
	}

	public PageResult<SerachSortPO> findSerachSortOfPage(SerachSortPO po, Pagination page) {
		
		PageResult<SerachSortPO> pageResult = new PageResult<SerachSortPO>();
		List<SerachSortPO> list = null;

		int cnt = serachSortReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = serachSortReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<SerachSortPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<SerachSortPO> findSerachSortAll(SerachSortPO po) {

		return serachSortReadDAO.findAll(po,null);
	}
	
}
	