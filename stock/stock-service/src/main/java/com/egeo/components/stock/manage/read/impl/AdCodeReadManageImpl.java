package com.egeo.components.stock.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.manage.read.AdCodeReadManage;
import com.egeo.components.stock.dao.read.AdCodeReadDAO;
import com.egeo.components.stock.po.AdCodePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class AdCodeReadManageImpl implements AdCodeReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AdCodeReadDAO adCodeReadDAO;
	
	public AdCodePO findAdCodeById(AdCodePO po) {
		AdCodePO adCodepo = new AdCodePO();
		adCodepo.setId(po.getId());
		return adCodeReadDAO.findById(adCodepo);
	}

	public PageResult<AdCodePO> findAdCodeOfPage(AdCodePO po, Pagination page) {
		
		PageResult<AdCodePO> pageResult = new PageResult<AdCodePO>();
		List<AdCodePO> list = null;

		int cnt = adCodeReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = adCodeReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<AdCodePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<AdCodePO> findAdCodeAll(AdCodePO po) {

		return adCodeReadDAO.findAll(po,null);
	}
	
}
	