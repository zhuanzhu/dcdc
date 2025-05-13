package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.StandardProductUnitPictureRecordWriteManage;
import com.egeo.components.product.dao.write.StandardProductUnitPictureRecordWriteDAO;
import com.egeo.components.product.po.StandardProductUnitPictureRecordPO;
import com.egeo.exception.BusinessException;

@Service
public class StandardProductUnitPictureRecordWriteManageImpl implements StandardProductUnitPictureRecordWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardProductUnitPictureRecordWriteDAO standardProductUnitPictureRecordWriteDAO;

	@Override
	public Long insertStandardProductUnitPictureRecordWithTx(StandardProductUnitPictureRecordPO po) {
		
		int i ;
		try {
				i = standardProductUnitPictureRecordWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateStandardProductUnitPictureRecordWithTx(StandardProductUnitPictureRecordPO po) {
		int i;
		i = standardProductUnitPictureRecordWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteStandardProductUnitPictureRecordWithTx(StandardProductUnitPictureRecordPO po) {
		int i;
		i = standardProductUnitPictureRecordWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	