package com.egeo.components.user.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.RoleMenuWriteManage;
import com.egeo.components.user.dao.write.RoleMenuWriteDAO;
import com.egeo.components.user.po.RoleMenuPO;

@Service
public class RoleMenuWriteManageImpl implements RoleMenuWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RoleMenuWriteDAO roleMenuWriteDAO;
	@Override
	public String saveWithTx(RoleMenuPO po) {
		
		return roleMenuWriteDAO.insert(po)+"";
	}
	@Override
	public int delete(RoleMenuPO po) {
		
		return roleMenuWriteDAO.deleteByPara(po);
	}
}
	