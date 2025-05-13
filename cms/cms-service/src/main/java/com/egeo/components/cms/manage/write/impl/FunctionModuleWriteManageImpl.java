package com.egeo.components.cms.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.dao.write.FunctionModuleWriteDAO;
import com.egeo.components.cms.manage.write.FunctionModuleWriteManage;
import com.egeo.components.cms.po.FunctionModulePO;
import com.egeo.exception.BusinessException;

@Service
public class FunctionModuleWriteManageImpl implements FunctionModuleWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private FunctionModuleWriteDAO functionModuleWriteDAO;

	@Override
	public Long insertFunctionModuleWithTx(FunctionModulePO po) {
		
		int i ;
		try {
				i = functionModuleWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateFunctionModuleWithTx(FunctionModulePO po) {
		int i;
		i = functionModuleWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteFunctionModuleWithTx(FunctionModulePO po) {
		int i;
		i = functionModuleWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	