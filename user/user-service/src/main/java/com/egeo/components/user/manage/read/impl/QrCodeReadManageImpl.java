package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.condition.QrCodeCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.read.QrCodeReadManage;
import com.egeo.components.user.dao.read.QrCodeReadDAO;
import com.egeo.components.user.po.QrCodePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class QrCodeReadManageImpl implements QrCodeReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private QrCodeReadDAO qrCodeReadDAO;
	
	public QrCodePO findQrCodeById(QrCodePO po) {
		QrCodePO qrCodepo = new QrCodePO();
		qrCodepo.setId(po.getId());
		return qrCodeReadDAO.findById(qrCodepo);
	}

	public PageResult<QrCodePO> findQrCodeOfPage(QrCodePO po, Pagination page) {
		
		PageResult<QrCodePO> pageResult = new PageResult<QrCodePO>();
		List<QrCodePO> list = null;

		int cnt = qrCodeReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = qrCodeReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<QrCodePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<QrCodePO> findQrCodeAll(QrCodePO po) {

		return qrCodeReadDAO.findAll(po,null);
	}

	@Override
	public List<QrCodeCondition> findQrCodeListByCouponUnitIds(List<Long> couponUnitIds) {
		return qrCodeReadDAO.findQrCodeListByCouponUnitIds(couponUnitIds);
	}

	@Override
	public QrCodeCondition findQrCodeByCouponUnitId(Long id) {
		return qrCodeReadDAO.findQrCodeByCouponUnitId(id);
	}

}
	