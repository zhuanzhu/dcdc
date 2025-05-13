package com.egeo.components.order.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.read.SoDeviceReadManage;
import com.egeo.components.order.dao.read.SoDeviceReadDAO;
import com.egeo.components.order.po.SoDevicePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class SoDeviceReadManageImpl implements SoDeviceReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SoDeviceReadDAO soDeviceReadDAO;
	
	public SoDevicePO findSoDeviceById(SoDevicePO po) {
		SoDevicePO soDevicepo = new SoDevicePO();
		soDevicepo.setId(po.getId());
		return soDeviceReadDAO.findById(soDevicepo);
	}

	public PageResult<SoDevicePO> findSoDeviceOfPage(SoDevicePO po, Pagination page) {
		
		PageResult<SoDevicePO> pageResult = new PageResult<SoDevicePO>();
		List<SoDevicePO> list = null;

		int cnt = soDeviceReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = soDeviceReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<SoDevicePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<SoDevicePO> findSoDeviceAll(SoDevicePO po) {

		return soDeviceReadDAO.findAll(po,null);
	}
	
}
	