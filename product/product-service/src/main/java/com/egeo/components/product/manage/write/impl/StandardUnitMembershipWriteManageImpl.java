package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.StandardUnitMembershipWriteManage;
import com.egeo.components.product.dao.write.StandardUnitMembershipWriteDAO;
import com.egeo.components.product.po.StandardUnitMembershipPO;
import com.egeo.exception.BusinessException;

@Service
public class StandardUnitMembershipWriteManageImpl implements StandardUnitMembershipWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardUnitMembershipWriteDAO standardUnitMembershipWriteDAO;

	@Override
	public Long insertStandardUnitMembershipWithTx(StandardUnitMembershipPO po) {
		
		int i ;
		try {
				i = standardUnitMembershipWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateStandardUnitMembershipWithTx(StandardUnitMembershipPO po) {
		int i;
		i = standardUnitMembershipWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteStandardUnitMembershipWithTx(StandardUnitMembershipPO po) {
		int i;
		i = standardUnitMembershipWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	