package com.egeo.components.order.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.write.SoFlowWriteManage;
import com.egeo.components.order.dao.write.SoFlowWriteDAO;
import com.egeo.components.order.po.SoFlowPO;
import com.egeo.exception.BusinessException;

@Service
public class SoFlowWriteManageImpl implements SoFlowWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SoFlowWriteDAO soFlowWriteDAO;

	@Override
	public Long insertSoFlowWithTx(SoFlowPO po) {
		
		int i ;
		try {
				i = soFlowWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateSoFlowWithTx(SoFlowPO po) {
		int i;
		i = soFlowWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteSoFlowWithTx(SoFlowPO po) {
		int i;
		i = soFlowWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	