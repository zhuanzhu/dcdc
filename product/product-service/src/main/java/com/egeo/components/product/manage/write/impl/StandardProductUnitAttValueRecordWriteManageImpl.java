package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.StandardProductUnitAttValueRecordWriteManage;
import com.egeo.components.product.dao.write.StandardProductUnitAttValueRecordWriteDAO;
import com.egeo.components.product.po.StandardProductUnitAttValueRecordPO;
import com.egeo.exception.BusinessException;

@Service
public class StandardProductUnitAttValueRecordWriteManageImpl implements StandardProductUnitAttValueRecordWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardProductUnitAttValueRecordWriteDAO standardProductUnitAttValueRecordWriteDAO;

	@Override
	public Long insertStandardProductUnitAttValueRecordWithTx(StandardProductUnitAttValueRecordPO po) {
		
		int i ;
		try {
				i = standardProductUnitAttValueRecordWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateStandardProductUnitAttValueRecordWithTx(StandardProductUnitAttValueRecordPO po) {
		int i;
		i = standardProductUnitAttValueRecordWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteStandardProductUnitAttValueRecordWithTx(StandardProductUnitAttValueRecordPO po) {
		int i;
		i = standardProductUnitAttValueRecordWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	