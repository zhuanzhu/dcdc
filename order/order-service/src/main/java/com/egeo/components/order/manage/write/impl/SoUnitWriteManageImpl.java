package com.egeo.components.order.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.write.SoUnitWriteManage;
import com.egeo.components.order.dao.write.SoUnitWriteDAO;
import com.egeo.components.order.po.SoUnitPO;
import com.egeo.exception.BusinessException;

@Service
public class SoUnitWriteManageImpl implements SoUnitWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SoUnitWriteDAO soUnitWriteDAO;

	@Override
	public Long insertSoUnitWithTx(SoUnitPO po) {
		
		int i ;
		try {
				i = soUnitWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateSoUnitWithTx(SoUnitPO po) {
		int i;
		i = soUnitWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteSoUnitWithTx(SoUnitPO po) {
		int i;
		i = soUnitWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	