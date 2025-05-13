package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.StandardProductUnitRecordWriteManage;
import com.egeo.components.product.dao.write.StandardProductUnitRecordWriteDAO;
import com.egeo.components.product.po.StandardProductUnitRecordPO;
import com.egeo.exception.BusinessException;

@Service
public class StandardProductUnitRecordWriteManageImpl implements StandardProductUnitRecordWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardProductUnitRecordWriteDAO standardProductUnitRecordWriteDAO;

	@Override
	public Long insertStandardProductUnitRecordWithTx(StandardProductUnitRecordPO po) {
		
		int i ;
		try {
				i = standardProductUnitRecordWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateStandardProductUnitRecordWithTx(StandardProductUnitRecordPO po) {
		int i;
		i = standardProductUnitRecordWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteStandardProductUnitRecordWithTx(StandardProductUnitRecordPO po) {
		int i;
		i = standardProductUnitRecordWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	