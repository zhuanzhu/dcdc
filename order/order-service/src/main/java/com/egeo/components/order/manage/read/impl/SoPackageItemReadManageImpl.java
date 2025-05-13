package com.egeo.components.order.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.read.SoPackageItemReadManage;
import com.egeo.components.order.dao.read.SoPackageItemReadDAO;
import com.egeo.components.order.po.SoPackageItemPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class SoPackageItemReadManageImpl implements SoPackageItemReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SoPackageItemReadDAO soPackageItemReadDAO;
	
	public SoPackageItemPO findSoPackageItemById(SoPackageItemPO po) {
		SoPackageItemPO soPackageItempo = new SoPackageItemPO();
		soPackageItempo.setId(po.getId());
		return soPackageItemReadDAO.findById(soPackageItempo);
	}

	public PageResult<SoPackageItemPO> findSoPackageItemOfPage(SoPackageItemPO po, Pagination page) {
		
		PageResult<SoPackageItemPO> pageResult = new PageResult<SoPackageItemPO>();
		List<SoPackageItemPO> list = null;

		int cnt = soPackageItemReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = soPackageItemReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<SoPackageItemPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<SoPackageItemPO> findSoPackageItemAll(SoPackageItemPO po) {

		return soPackageItemReadDAO.findAll(po,null);
	}
	
}
	