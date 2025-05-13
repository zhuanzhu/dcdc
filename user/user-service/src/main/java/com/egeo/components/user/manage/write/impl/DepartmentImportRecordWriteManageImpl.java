package com.egeo.components.user.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.DepartmentImportRecordWriteManage;
import com.egeo.components.user.dao.write.DepartmentImportRecordWriteDAO;
import com.egeo.components.user.po.DepartmentImportRecordPO;
import com.egeo.exception.BusinessException;

@Service
public class DepartmentImportRecordWriteManageImpl implements DepartmentImportRecordWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DepartmentImportRecordWriteDAO departmentImportRecordWriteDAO;

	@Override
	public Long insertDepartmentImportRecordWithTx(DepartmentImportRecordPO po) {
		
		int i ;
		try {
				i = departmentImportRecordWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateDepartmentImportRecordWithTx(DepartmentImportRecordPO po) {
		int i;
		i = departmentImportRecordWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteDepartmentImportRecordWithTx(DepartmentImportRecordPO po) {
		int i;
		i = departmentImportRecordWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	