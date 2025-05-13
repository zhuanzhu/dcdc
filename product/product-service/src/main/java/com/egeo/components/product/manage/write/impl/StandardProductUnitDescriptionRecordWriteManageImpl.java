package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.StandardProductUnitDescriptionRecordWriteManage;
import com.egeo.components.product.dao.write.StandardProductUnitDescriptionRecordWriteDAO;
import com.egeo.components.product.po.StandardProductUnitDescriptionRecordPO;
import com.egeo.exception.BusinessException;

@Service
public class StandardProductUnitDescriptionRecordWriteManageImpl implements StandardProductUnitDescriptionRecordWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardProductUnitDescriptionRecordWriteDAO standardProductUnitDescriptionRecordWriteDAO;

	@Override
	public Long insertStandardProductUnitDescriptionRecordWithTx(StandardProductUnitDescriptionRecordPO po) {
		
		int i ;
		try {
				i = standardProductUnitDescriptionRecordWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateStandardProductUnitDescriptionRecordWithTx(StandardProductUnitDescriptionRecordPO po) {
		int i;
		i = standardProductUnitDescriptionRecordWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteStandardProductUnitDescriptionRecordWithTx(StandardProductUnitDescriptionRecordPO po) {
		int i;
		i = standardProductUnitDescriptionRecordWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	