package com.egeo.components.order.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.read.SoThirdpartyReadManage;
import com.egeo.components.order.dao.read.SoThirdpartyReadDAO;
import com.egeo.components.order.po.SoThirdpartyPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class SoThirdpartyReadManageImpl implements SoThirdpartyReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SoThirdpartyReadDAO soThirdpartyReadDAO;
	
	public SoThirdpartyPO findSoThirdpartyById(SoThirdpartyPO po) {
		SoThirdpartyPO soThirdpartypo = new SoThirdpartyPO();
		soThirdpartypo.setId(po.getId());
		return soThirdpartyReadDAO.findById(soThirdpartypo);
	}

	public PageResult<SoThirdpartyPO> findSoThirdpartyOfPage(SoThirdpartyPO po, Pagination page) {
		
		PageResult<SoThirdpartyPO> pageResult = new PageResult<SoThirdpartyPO>();
		List<SoThirdpartyPO> list = null;

		int cnt = soThirdpartyReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = soThirdpartyReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<SoThirdpartyPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<SoThirdpartyPO> findSoThirdpartyAll(SoThirdpartyPO po) {

		return soThirdpartyReadDAO.findAll(po,null);
	}

	@Override
	public List<Long> getThirdpartyIdListByStatus() {
		return soThirdpartyReadDAO.getThirdpartyIdListByStatus();
	}

	@Override
	public Long findSoChildIdByThirdpartyId(Long jdOrderId) {
		return soThirdpartyReadDAO.findSoChildIdByThirdpartyId(jdOrderId);
	}


}
	