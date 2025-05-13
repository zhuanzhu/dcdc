package com.egeo.components.user.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.CodeModuleWriteManage;
import com.egeo.components.user.dao.write.CodeModuleWriteDAO;
import com.egeo.components.user.po.CodeModulePO;
import com.egeo.exception.BusinessException;

@Service
public class CodeModuleWriteManageImpl implements CodeModuleWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CodeModuleWriteDAO codeModuleWriteDAO;

	@Override
	public Long insertCodeModuleWithTx(CodeModulePO po) {
		
		int i ;
		try {
				i = codeModuleWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateCodeModuleWithTx(CodeModulePO po) {
		int i;
		i = codeModuleWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteCodeModuleWithTx(CodeModulePO po) {
		int i;
		i = codeModuleWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	