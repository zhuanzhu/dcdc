package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.read.PayTypeReadManage;
import com.egeo.components.user.dao.read.PayTypeReadDAO;
import com.egeo.components.user.po.PayTypePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class PayTypeReadManageImpl implements PayTypeReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PayTypeReadDAO payTypeReadDAO;
	
	public PayTypePO findPayTypeById(PayTypePO po) {
		PayTypePO payTypepo = new PayTypePO();
		payTypepo.setId(po.getId());
		return payTypeReadDAO.findById(payTypepo);
	}

	public PageResult<PayTypePO> findPayTypeOfPage(PayTypePO po, Pagination page) {
		
		PageResult<PayTypePO> pageResult = new PageResult<PayTypePO>();
		List<PayTypePO> list = null;

		int cnt = payTypeReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = payTypeReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<PayTypePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<PayTypePO> findPayTypeAll(PayTypePO po) {

		return payTypeReadDAO.findAll(po,null);
	}

	@Override
	public PayTypePO findPayTypeByCode(Integer code) {
		return payTypeReadDAO.findByCode(code);
	}

	@Override
	public List<PayTypePO> findPayTypeByCodes(List<Integer> payTypeCodes) {
		return payTypeReadDAO.findByCodes(payTypeCodes);
	}

}
	