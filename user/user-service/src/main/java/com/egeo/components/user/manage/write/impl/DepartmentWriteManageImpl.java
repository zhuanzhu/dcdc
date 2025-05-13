package com.egeo.components.user.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.DepartmentWriteManage;
import com.egeo.components.user.dao.read.UserReadDAO;
import com.egeo.components.user.dao.write.DepartmentWriteDAO;
import com.egeo.components.user.dao.write.UserWelfareWriteDAO;
import com.egeo.components.user.po.DepartmentPO;
import com.egeo.components.user.po.UserPO;
import com.egeo.exception.BusinessException;

@Service
public class DepartmentWriteManageImpl implements DepartmentWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DepartmentWriteDAO departmentWriteDAO;
	
	@Autowired
	private UserWelfareWriteDAO userWelfareWriteDAO;
	
	@Autowired
	private UserReadDAO userReadDAO;

	@Override
	public Long insertDepartmentWithTx(DepartmentPO po) {
		
		int i ;
		try {
				i = departmentWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateDepartmentWithTx(DepartmentPO po) {
		int i;
		i = departmentWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteDepartmentWithTx(DepartmentPO po) {
		int i;
		i = departmentWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public Long deleteByCompanyIdWithTx(DepartmentPO po) {
		int i;
		i = departmentWriteDAO.deleteByPara(po);
		//根据公司id查询公司下面所有的员工信息
		List<UserPO> userList = userReadDAO.findUsersByCompanyId(po.getCompanyId());
		for (UserPO userPO : userList) {
			//根据用户id删除员工部门信息
			userWelfareWriteDAO.updateDepartmentIsNullByUserId(userPO.getId());
		}
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return (long)i;
	}	
}
	