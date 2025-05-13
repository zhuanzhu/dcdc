package com.egeo.components.promotion.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.manage.read.ECardTempReadManage;
import com.egeo.components.promotion.dao.read.ECardTempReadDAO;
import com.egeo.components.promotion.po.ECardTempPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class ECardTempReadManageImpl implements ECardTempReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ECardTempReadDAO eCardTempReadDAO;
	
	public ECardTempPO findECardTempById(ECardTempPO po) {
		ECardTempPO eCardTemppo = new ECardTempPO();
		eCardTemppo.setId(po.getId());
		return eCardTempReadDAO.findById(eCardTemppo);
	}

	public PageResult<ECardTempPO> findECardTempOfPage(ECardTempPO po, Pagination page) {
		
		PageResult<ECardTempPO> pageResult = new PageResult<ECardTempPO>();
		List<ECardTempPO> list = null;

		int cnt = eCardTempReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = eCardTempReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<ECardTempPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<ECardTempPO> findECardTempAll(ECardTempPO po) {

		return eCardTempReadDAO.findAll(po,null);
	}
	
}
	