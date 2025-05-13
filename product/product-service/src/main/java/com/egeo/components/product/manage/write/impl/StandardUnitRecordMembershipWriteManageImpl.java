package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.StandardUnitRecordMembershipWriteManage;
import com.egeo.components.product.dao.write.StandardUnitRecordMembershipWriteDAO;
import com.egeo.components.product.po.StandardUnitRecordMembershipPO;
import com.egeo.exception.BusinessException;

@Service
public class StandardUnitRecordMembershipWriteManageImpl implements StandardUnitRecordMembershipWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardUnitRecordMembershipWriteDAO standardUnitRecordMembershipWriteDAO;

	@Override
	public Long insertStandardUnitRecordMembershipWithTx(StandardUnitRecordMembershipPO po) {
		
		int i ;
		try {
				i = standardUnitRecordMembershipWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateStandardUnitRecordMembershipWithTx(StandardUnitRecordMembershipPO po) {
		int i;
		i = standardUnitRecordMembershipWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteStandardUnitRecordMembershipWithTx(StandardUnitRecordMembershipPO po) {
		int i;
		i = standardUnitRecordMembershipWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	