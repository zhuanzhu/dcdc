package com.egeo.components.user.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.FunctionTreeNodeWriteManage;
import com.egeo.components.user.dao.write.FunctionTreeNodeWriteDAO;
import com.egeo.components.user.po.FunctionTreeNodePO;
import com.egeo.exception.BusinessException;

@Service
public class FunctionTreeNodeWriteManageImpl implements FunctionTreeNodeWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private FunctionTreeNodeWriteDAO functionTreeNodeWriteDAO;

	@Override
	public Long insertFunctionTreeNodeWithTx(FunctionTreeNodePO po) {
		
		int i ;
		try {
				i = functionTreeNodeWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateFunctionTreeNodeWithTx(FunctionTreeNodePO po) {
		int i;
		i = functionTreeNodeWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteFunctionTreeNodeWithTx(FunctionTreeNodePO po) {
		int i;
		i = functionTreeNodeWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	