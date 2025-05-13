package com.egeo.components.user.manage.write.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.dao.write.RoleFunctionTreeNodeWriteDAO;
import com.egeo.components.user.dao.write.RoleMenuWriteDAO;
import com.egeo.components.user.dao.write.RoleUrlWriteDAO;
import com.egeo.components.user.dao.write.RoleWriteDAO;
import com.egeo.components.user.manage.write.RoleWriteManage;
import com.egeo.components.user.po.RoleFunctionTreeNodePO;
import com.egeo.components.user.po.RoleMenuPO;
import com.egeo.components.user.po.RolePO;
import com.egeo.components.user.po.RoleUrl;
import com.egeo.components.user.po.RoleUrlPO;
import com.egeo.utils.StringUtils;

@Service
public class RoleWriteManageImpl implements RoleWriteManage {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RoleWriteDAO roleWriteDAO;

	@Autowired
	private RoleMenuWriteDAO roleMenuWriteDAO;

	@Autowired
	private RoleUrlWriteDAO roleUrlWriteDAO;

	@Autowired
	private RoleFunctionTreeNodeWriteDAO roleFunctionTreeNodeWriteDAO;

	@Override
	public Long saveRoleWithTx(RolePO po, List<Long> menuIdList, List<Long> urlIdList, List<Long> functionIdList) {
		roleWriteDAO.insert(po);
		Long roleId = po.getId();
		Long platformId = po.getPlatformId();
		insertRoleMenu(roleId, menuIdList, platformId);
		insertRoleUrl(roleId, urlIdList, platformId);
		insertRoleFunction(roleId, functionIdList, platformId);
		return roleId;
	}

	private void insertRoleMenu(Long roleId, List<Long> menuIdList, Long platformId) {
		if (StringUtils.isNotEmpty(menuIdList)) {
			for (Long menuId : menuIdList) {
				RoleMenuPO po = new RoleMenuPO();
				po.setRoleId(roleId);
				po.setMenuId(menuId);
				roleMenuWriteDAO.insert(po);
			}
		}
	}

	private void insertRoleUrl(Long roleId, List<Long> urlIdList, Long platformId) {
		if (StringUtils.isNotEmpty(urlIdList)) {
			for (Long urlId : urlIdList) {
				RoleUrlPO po = new RoleUrlPO();
				po.setRoleId(roleId);
				po.setUrlId(urlId);
				po.setPlatformId(platformId);
				roleUrlWriteDAO.insert(po);
			}
		}
	}

	private void insertRoleFunction(Long roleId, List<Long> functionIdList, Long platformId) {
		if (StringUtils.isNotEmpty(functionIdList)) {
			for (Long functionId : functionIdList) {
				RoleFunctionTreeNodePO po = new RoleFunctionTreeNodePO();
				po.setRoleId(roleId);
				po.setFunctionTreeNodeId(functionId);
				po.setPlatformId(platformId);
				roleFunctionTreeNodeWriteDAO.insert(po);
			}
		}
	}
	
	@Override
	public int updateRoleWithTx(RolePO po, List<Long> menuIdList, List<Long> urlIdList, List<Long> functionIdList) {
		Long roleId = po.getId();
		Long platformId = po.getPlatformId();
		deleteRoleUrl(roleId);
		insertRoleUrl(roleId, urlIdList, platformId);
		
		deleteRoleFunction(roleId);
		insertRoleFunction(roleId, functionIdList, platformId);

		po.setUpdateTime(new Date());
		deleteRoleMenu(roleId);
		insertRoleMenu(roleId, menuIdList, platformId);

		return roleWriteDAO.update(po);
	}
	
	private int deleteRoleMenu(Long roleId) {
		RoleMenuPO po = new RoleMenuPO();
		po.setRoleId(roleId);
		return roleMenuWriteDAO.deleteByPara(po);
	}
	
	private int deleteRoleUrl(Long roleId) {
		RoleUrlPO po = new RoleUrlPO();
		po.setRoleId(roleId);
		return roleUrlWriteDAO.deleteByPara(po);
	}
	
	private int deleteRoleFunction(Long roleId) {
		RoleFunctionTreeNodePO po = new RoleFunctionTreeNodePO();
		po.setRoleId(roleId);
		return roleFunctionTreeNodeWriteDAO.deleteByPara(po);
	}
	
	@Override
	public int deleteByIdWithTx(RolePO po) {
		int i = 0;
		Long roleId = po.getId();
		i += deleteRoleMenu(roleId);
		i += deleteRoleUrl(roleId);
		i += deleteRoleFunction(roleId);
		i += roleWriteDAO.delete(po);
		return i;
	}

	@Override
	public void updateRoleStatus(RolePO po, Integer useable) {
		roleWriteDAO.updateRoleStatus(po, useable);
	}


	@Override
	public int removeRoleWithTx(String name) {

		RolePO po = new RolePO();
		po.setName(name);
		return roleWriteDAO.delete(po);

	}

	@Override
	public int upDate(RolePO po) {

		return roleWriteDAO.update(po);
	}

	@Override
	public int delMenu(StringBuffer delMenu, Long roleId) {
		String menus = delMenu.toString();
		RoleUrl roleMenu = new RoleUrl();
		roleMenu.setRoleId(roleId);
		roleMenu.setUrls(menus);
		return roleWriteDAO.delMenu(roleMenu);
	}

	@Override
	public int setMenu(List<String> setMenu, Long roleId) {
		int row = 0;
		for (String s : setMenu) {
			RoleMenuPO po = new RoleMenuPO();
			po.setRoleId(roleId);
			// long result = Long.parseLong(s); 返回long基本数据类型
			Long result = Long.valueOf(s);// 返回Long包装类型
			po.setMenuId(result);
			row += roleMenuWriteDAO.insert(po);
		}
		return row;
	}

}
