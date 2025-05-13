package com.egeo.components.order.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.read.SoPackageTempReadManage;
import com.egeo.components.order.dao.read.SoPackageTempReadDAO;
import com.egeo.components.order.po.SoPackageTempPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class SoPackageTempReadManageImpl implements SoPackageTempReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SoPackageTempReadDAO soPackageTempReadDAO;
	
	public SoPackageTempPO findSoPackageTempById(SoPackageTempPO po) {
		SoPackageTempPO soPackageTemppo = new SoPackageTempPO();
		soPackageTemppo.setId(po.getId());
		return soPackageTempReadDAO.findById(soPackageTemppo);
	}

	public PageResult<SoPackageTempPO> findSoPackageTempOfPage(SoPackageTempPO po, Pagination page) {
		
		PageResult<SoPackageTempPO> pageResult = new PageResult<SoPackageTempPO>();
		List<SoPackageTempPO> list = null;

		int cnt = soPackageTempReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = soPackageTempReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<SoPackageTempPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<SoPackageTempPO> findSoPackageTempAll(SoPackageTempPO po) {

		return soPackageTempReadDAO.findAll(po,null);
	}
	
}
	