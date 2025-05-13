package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.StandardUnitCompanyRecordWriteManage;
import com.egeo.components.product.dao.write.StandardUnitCompanyRecordWriteDAO;
import com.egeo.components.product.po.StandardUnitCompanyRecordPO;
import com.egeo.exception.BusinessException;

@Service
public class StandardUnitCompanyRecordWriteManageImpl implements StandardUnitCompanyRecordWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardUnitCompanyRecordWriteDAO standardUnitCompanyRecordWriteDAO;

	@Override
	public Long insertStandardUnitCompanyRecordWithTx(StandardUnitCompanyRecordPO po) {
		
		int i ;
		try {
				i = standardUnitCompanyRecordWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateStandardUnitCompanyRecordWithTx(StandardUnitCompanyRecordPO po) {
		int i;
		i = standardUnitCompanyRecordWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteStandardUnitCompanyRecordWithTx(StandardUnitCompanyRecordPO po) {
		int i;
		i = standardUnitCompanyRecordWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	