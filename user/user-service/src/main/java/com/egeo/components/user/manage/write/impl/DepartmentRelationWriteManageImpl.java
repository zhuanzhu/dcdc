package com.egeo.components.user.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.DepartmentRelationWriteManage;
import com.egeo.components.user.dao.write.DepartmentRelationWriteDAO;
import com.egeo.components.user.po.DepartmentRelationPO;
import com.egeo.exception.BusinessException;

@Service
public class DepartmentRelationWriteManageImpl implements DepartmentRelationWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DepartmentRelationWriteDAO departmentRelationWriteDAO;

	@Override
	public Long insertDepartmentRelationWithTx(DepartmentRelationPO po) {
		
		int i ;
		try {
				i = departmentRelationWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateDepartmentRelationWithTx(DepartmentRelationPO po) {
		int i;
		i = departmentRelationWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteDepartmentRelationWithTx(DepartmentRelationPO po) {
		int i;
		i = departmentRelationWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public int deleteDepartmentRelationByDepartmentIdWithTx(DepartmentRelationPO po) {
		int i;
		i = departmentRelationWriteDAO.deleteByPara(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	