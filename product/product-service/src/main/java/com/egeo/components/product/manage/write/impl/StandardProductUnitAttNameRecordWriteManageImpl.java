package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.StandardProductUnitAttNameRecordWriteManage;
import com.egeo.components.product.dao.write.StandardProductUnitAttNameRecordWriteDAO;
import com.egeo.components.product.po.StandardProductUnitAttNameRecordPO;
import com.egeo.exception.BusinessException;

@Service
public class StandardProductUnitAttNameRecordWriteManageImpl implements StandardProductUnitAttNameRecordWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardProductUnitAttNameRecordWriteDAO standardProductUnitAttNameRecordWriteDAO;

	@Override
	public Long insertStandardProductUnitAttNameRecordWithTx(StandardProductUnitAttNameRecordPO po) {
		
		int i ;
		try {
				i = standardProductUnitAttNameRecordWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateStandardProductUnitAttNameRecordWithTx(StandardProductUnitAttNameRecordPO po) {
		int i;
		i = standardProductUnitAttNameRecordWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteStandardProductUnitAttNameRecordWithTx(StandardProductUnitAttNameRecordPO po) {
		int i;
		i = standardProductUnitAttNameRecordWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	