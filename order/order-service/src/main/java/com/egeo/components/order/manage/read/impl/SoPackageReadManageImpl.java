package com.egeo.components.order.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.read.SoPackageReadManage;
import com.egeo.components.order.condition.SoPackageCondition;
import com.egeo.components.order.dao.read.SoPackageReadDAO;
import com.egeo.components.order.po.SoPackagePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class SoPackageReadManageImpl implements SoPackageReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SoPackageReadDAO soPackageReadDAO;
	
	public SoPackagePO findSoPackageById(SoPackagePO po) {
		SoPackagePO soPackagepo = new SoPackagePO();
		soPackagepo.setId(po.getId());
		return soPackageReadDAO.findById(soPackagepo);
	}

	public PageResult<SoPackagePO> findSoPackageOfPage(SoPackagePO po, Pagination page) {
		
		PageResult<SoPackagePO> pageResult = new PageResult<SoPackagePO>();
		List<SoPackagePO> list = null;

		int cnt = soPackageReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = soPackageReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<SoPackagePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<SoPackagePO> findSoPackageAll(SoPackagePO po) {

		return soPackageReadDAO.findAll(po,null);
	}
	@Override
	public List<SoPackagePO> findUnReceive(SoPackagePO po) {

		return soPackageReadDAO.findUnReceive(po);
	}
	
	@Override
	public List<SoPackagePO> packageByOrderCode(String orderCode) {
		
		return soPackageReadDAO.packageByOrderCode(orderCode);
	}

	@Override
	public PageResult<SoPackageCondition> findSoPackageAndBoxOfPage(SoPackagePO po, Pagination page) {
		
		PageResult<SoPackageCondition> pageResult = new PageResult<SoPackageCondition>();
		List<SoPackageCondition> list = null;

		int cnt = soPackageReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = soPackageReadDAO.findSoPackageAndBoxOfPage(po, page);
		} else {
			list = new ArrayList<SoPackageCondition>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	@Override
	public List<SoPackagePO> queryPackageBySoChildId(Long id) {
		
		return soPackageReadDAO.queryPackageBySoChildId(id);
	}

	@Override
	public String findDeliveryCompanyNameBySoChildId(Long soChildId) {
		return soPackageReadDAO.findDeliveryCompanyNameBySoChildId(soChildId);
	}

}
	