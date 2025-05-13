package com.egeo.components.order.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.read.SoPackageBoxReadManage;
import com.egeo.components.order.dao.read.SoPackageBoxReadDAO;
import com.egeo.components.order.po.SoPackageBoxPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class SoPackageBoxReadManageImpl implements SoPackageBoxReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SoPackageBoxReadDAO soPackageBoxReadDAO;
	
	public SoPackageBoxPO findSoPackageBoxById(SoPackageBoxPO po) {
		SoPackageBoxPO soPackageBoxpo = new SoPackageBoxPO();
		soPackageBoxpo.setId(po.getId());
		return soPackageBoxReadDAO.findById(soPackageBoxpo);
	}

	public PageResult<SoPackageBoxPO> findSoPackageBoxOfPage(SoPackageBoxPO po, Pagination page) {
		
		PageResult<SoPackageBoxPO> pageResult = new PageResult<SoPackageBoxPO>();
		List<SoPackageBoxPO> list = null;

		int cnt = soPackageBoxReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = soPackageBoxReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<SoPackageBoxPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<SoPackageBoxPO> findSoPackageBoxAll(SoPackageBoxPO po) {

		return soPackageBoxReadDAO.findAll(po,null);
	}

	@Override
	public SoPackageBoxPO queryBoxByBoxCodeAndChildId(Long boxCode, Long soChildId) {
		return soPackageBoxReadDAO.queryBoxByBoxCodeAndChildId(boxCode,soChildId);
	}
	
}
	