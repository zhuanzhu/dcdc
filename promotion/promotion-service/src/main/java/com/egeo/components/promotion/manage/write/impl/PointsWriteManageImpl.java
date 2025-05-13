package com.egeo.components.promotion.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.dao.write.PointsWriteDAO;
import com.egeo.components.promotion.manage.write.PointsWriteManage;
import com.egeo.components.promotion.po.PointsPO;
import com.egeo.exception.BusinessException;

@Service
public class PointsWriteManageImpl implements PointsWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PointsWriteDAO pointsWriteDAO;

	@Override
	public Long insertPointsWithTx(PointsPO po) {
		
		int i ;
		try {
				i = pointsWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updatePointsWithTx(PointsPO po) {
		int i;
		i = pointsWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deletePointsWithTx(PointsPO po) {
		int i;
		i = pointsWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	