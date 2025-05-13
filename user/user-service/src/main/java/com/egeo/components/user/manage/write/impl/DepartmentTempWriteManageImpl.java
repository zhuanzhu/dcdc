package com.egeo.components.user.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.DepartmentTempWriteManage;
import com.egeo.components.user.dao.write.DepartmentTempWriteDAO;
import com.egeo.components.user.po.DepartmentTempPO;
import com.egeo.exception.BusinessException;

@Service
public class DepartmentTempWriteManageImpl implements DepartmentTempWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DepartmentTempWriteDAO departmentTempWriteDAO;

	@Override
	public Long insertDepartmentTempWithTx(DepartmentTempPO po) {
		
		int i ;
		try {
				i = departmentTempWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateDepartmentTempWithTx(DepartmentTempPO po) {
		int i;
		i = departmentTempWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteDepartmentTempWithTx(DepartmentTempPO po) {
		int i;
		i = departmentTempWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	