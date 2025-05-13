package com.egeo.components.order.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.write.FrozenPuWriteManage;
import com.egeo.components.order.dao.write.FrozenPuWriteDAO;
import com.egeo.components.order.po.FrozenPuPO;
import com.egeo.exception.BusinessException;

@Service
public class FrozenPuWriteManageImpl implements FrozenPuWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private FrozenPuWriteDAO frozenPuWriteDAO;

	@Override
	public Long insertFrozenPuWithTx(FrozenPuPO po) {
		
		int i ;
		try {
				i = frozenPuWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateFrozenPuWithTx(FrozenPuPO po) {
		int i;
		i = frozenPuWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteFrozenPuWithTx(FrozenPuPO po) {
		int i;
		i = frozenPuWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	