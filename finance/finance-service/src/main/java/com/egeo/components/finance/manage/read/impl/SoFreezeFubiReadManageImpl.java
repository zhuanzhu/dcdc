package com.egeo.components.finance.manage.read.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.finance.dao.read.SoFreezeFubiReadDAO;
import com.egeo.components.finance.manage.read.SoFreezeFubiReadManage;
import com.egeo.components.finance.po.SoFreezeFubiPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class SoFreezeFubiReadManageImpl implements SoFreezeFubiReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SoFreezeFubiReadDAO soFreezeFubiReadDAO;
	
	public SoFreezeFubiPO findSoFreezeFubiById(SoFreezeFubiPO po) {
		SoFreezeFubiPO soFreezeFubipo = new SoFreezeFubiPO();
		soFreezeFubipo.setId(po.getId());
		return soFreezeFubiReadDAO.findById(soFreezeFubipo);
	}

	public PageResult<SoFreezeFubiPO> findSoFreezeFubiOfPage(SoFreezeFubiPO po, Pagination page) {
		
		PageResult<SoFreezeFubiPO> pageResult = new PageResult<SoFreezeFubiPO>();
		List<SoFreezeFubiPO> list = null;

		int cnt = soFreezeFubiReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = soFreezeFubiReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<SoFreezeFubiPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<SoFreezeFubiPO> findSoFreezeFubiAll(SoFreezeFubiPO po) {

		return soFreezeFubiReadDAO.findAll(po,null);
	}
	/**
	 * 根据订单id查询订单冻结积分
	 * @param soId
	 * @return
	 */
	@Override
	public BigDecimal findSoFreezeBalanceBySoId(Long soId) {
		return soFreezeFubiReadDAO.findSoFreezeBalanceBySoId(soId);
	}
	
}
	