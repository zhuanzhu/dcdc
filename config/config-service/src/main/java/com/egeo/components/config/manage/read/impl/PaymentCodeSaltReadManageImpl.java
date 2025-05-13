package com.egeo.components.config.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.config.dao.read.PaymentCodeSaltReadDAO;
import com.egeo.components.config.manage.read.PaymentCodeSaltReadManage;
import com.egeo.components.config.po.PaymentCodeSaltPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class PaymentCodeSaltReadManageImpl implements PaymentCodeSaltReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PaymentCodeSaltReadDAO paymentCodeSaltReadDAO;
	
	public PaymentCodeSaltPO findPaymentCodeSaltById(PaymentCodeSaltPO po) {
		PaymentCodeSaltPO paymentCodeSaltpo = new PaymentCodeSaltPO();
		paymentCodeSaltpo.setId(po.getId());
		return paymentCodeSaltReadDAO.findById(paymentCodeSaltpo);
	}

	public PageResult<PaymentCodeSaltPO> findPaymentCodeSaltOfPage(PaymentCodeSaltPO po, Pagination page) {
		
		PageResult<PaymentCodeSaltPO> pageResult = new PageResult<PaymentCodeSaltPO>();
		List<PaymentCodeSaltPO> list = null;

		int cnt = paymentCodeSaltReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = paymentCodeSaltReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<PaymentCodeSaltPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<PaymentCodeSaltPO> findPaymentCodeSaltAll(PaymentCodeSaltPO po) {

		return paymentCodeSaltReadDAO.findAll(po,null);
	}
	/**
	 * 根据uuId查询用户支付密钥
	 * @param paymentCodeUuid
	 * @return
	 */
	@Override
	public String findSaltByUUID(String paymentCodeUuid) {
		// TODO Auto-generated method stub
		return paymentCodeSaltReadDAO.findSaltByUUID(paymentCodeUuid);
	}
	
}
	