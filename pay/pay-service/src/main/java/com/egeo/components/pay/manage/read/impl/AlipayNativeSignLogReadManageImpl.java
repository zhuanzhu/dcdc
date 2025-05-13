package com.egeo.components.pay.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.pay.dao.read.AlipayNativeSignLogReadDAO;
import com.egeo.components.pay.manage.read.AlipayNativeSignLogReadManage;
import com.egeo.components.pay.po.AlipayNativeSignLogPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class AlipayNativeSignLogReadManageImpl implements AlipayNativeSignLogReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AlipayNativeSignLogReadDAO alipayNativeSignLogReadDAO;
	
	public AlipayNativeSignLogPO findAlipayNativeSignLogById(AlipayNativeSignLogPO po) {
		AlipayNativeSignLogPO alipayNativeSignLogpo = new AlipayNativeSignLogPO();
		alipayNativeSignLogpo.setId(po.getId());
		return alipayNativeSignLogReadDAO.findById(alipayNativeSignLogpo);
	}

	public PageResult<AlipayNativeSignLogPO> findAlipayNativeSignLogOfPage(AlipayNativeSignLogPO po, Pagination page) {
		
		PageResult<AlipayNativeSignLogPO> pageResult = new PageResult<AlipayNativeSignLogPO>();
		List<AlipayNativeSignLogPO> list = null;

		int cnt = alipayNativeSignLogReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = alipayNativeSignLogReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<AlipayNativeSignLogPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<AlipayNativeSignLogPO> findAlipayNativeSignLogAll(AlipayNativeSignLogPO po) {

		return alipayNativeSignLogReadDAO.findAll(po,null);
	}
	
}
	