package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.StandardUnitPictureRecordWriteManage;
import com.egeo.components.product.dao.write.StandardUnitPictureRecordWriteDAO;
import com.egeo.components.product.po.StandardUnitPictureRecordPO;
import com.egeo.exception.BusinessException;

@Service
public class StandardUnitPictureRecordWriteManageImpl implements StandardUnitPictureRecordWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardUnitPictureRecordWriteDAO standardUnitPictureRecordWriteDAO;

	@Override
	public Long insertStandardUnitPictureRecordWithTx(StandardUnitPictureRecordPO po) {
		
		int i ;
		try {
				i = standardUnitPictureRecordWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateStandardUnitPictureRecordWithTx(StandardUnitPictureRecordPO po) {
		int i;
		i = standardUnitPictureRecordWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteStandardUnitPictureRecordWithTx(StandardUnitPictureRecordPO po) {
		int i;
		i = standardUnitPictureRecordWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	