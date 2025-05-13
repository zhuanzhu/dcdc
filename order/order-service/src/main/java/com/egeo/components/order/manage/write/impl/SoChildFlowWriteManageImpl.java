package com.egeo.components.order.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.write.SoChildFlowWriteManage;
import com.egeo.components.order.dao.write.SoChildFlowWriteDAO;
import com.egeo.components.order.po.SoChildFlowPO;
import com.egeo.exception.BusinessException;

@Service
public class SoChildFlowWriteManageImpl implements SoChildFlowWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SoChildFlowWriteDAO soChildFlowWriteDAO;

	@Override
	public Long insertSoChildFlowWithTx(SoChildFlowPO po) {
		
		int i ;
		try {
				i = soChildFlowWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateSoChildFlowWithTx(SoChildFlowPO po) {
		int i;
		i = soChildFlowWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteSoChildFlowWithTx(SoChildFlowPO po) {
		int i;
		i = soChildFlowWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	