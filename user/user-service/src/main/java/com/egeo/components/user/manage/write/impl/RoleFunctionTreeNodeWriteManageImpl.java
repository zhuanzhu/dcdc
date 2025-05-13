package com.egeo.components.user.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.RoleFunctionTreeNodeWriteManage;
import com.egeo.components.user.dao.write.RoleFunctionTreeNodeWriteDAO;
import com.egeo.components.user.po.RoleFunctionTreeNodePO;
import com.egeo.exception.BusinessException;

@Service
public class RoleFunctionTreeNodeWriteManageImpl implements RoleFunctionTreeNodeWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RoleFunctionTreeNodeWriteDAO roleFunctionTreeNodeWriteDAO;

	@Override
	public Long insertRoleFunctionTreeNodeWithTx(RoleFunctionTreeNodePO po) {
		
		int i ;
		try {
				i = roleFunctionTreeNodeWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateRoleFunctionTreeNodeWithTx(RoleFunctionTreeNodePO po) {
		int i;
		i = roleFunctionTreeNodeWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteRoleFunctionTreeNodeWithTx(RoleFunctionTreeNodePO po) {
		int i;
		i = roleFunctionTreeNodeWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	