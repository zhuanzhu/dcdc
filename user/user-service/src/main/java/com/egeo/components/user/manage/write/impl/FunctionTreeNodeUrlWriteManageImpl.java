package com.egeo.components.user.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.FunctionTreeNodeUrlWriteManage;
import com.egeo.components.user.dao.write.FunctionTreeNodeUrlWriteDAO;
import com.egeo.components.user.po.FunctionTreeNodeUrlPO;
import com.egeo.exception.BusinessException;

@Service
public class FunctionTreeNodeUrlWriteManageImpl implements FunctionTreeNodeUrlWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private FunctionTreeNodeUrlWriteDAO functionTreeNodeUrlWriteDAO;

	@Override
	public Long insertFunctionTreeNodeUrlWithTx(FunctionTreeNodeUrlPO po) {
		
		int i ;
		try {
				i = functionTreeNodeUrlWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateFunctionTreeNodeUrlWithTx(FunctionTreeNodeUrlPO po) {
		int i;
		i = functionTreeNodeUrlWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteFunctionTreeNodeUrlWithTx(FunctionTreeNodeUrlPO po) {
		int i;
		i = functionTreeNodeUrlWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	