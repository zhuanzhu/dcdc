package com.egeo.components.order.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.write.SoItemWriteManage;
import com.egeo.components.order.dao.write.SoItemWriteDAO;
import com.egeo.components.order.po.SoItemPO;

@Service
public class SoItemWriteManageImpl implements SoItemWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SoItemWriteDAO soItemWriteDAO;

	@Override
	public Long updateSoItemWithTx(SoItemPO po) {
		soItemWriteDAO.update(po);
		return po.getId();
	}

	@Override
	public int updateSoItemRefundWithTx(SoItemPO po){

		return soItemWriteDAO.updateSoItemRefundWithTx(po);
	}
}
