package com.egeo.components.user.service.write.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.RoleMenuConverter;
import com.egeo.components.user.dto.RoleMenuDTO;
import com.egeo.components.user.manage.write.RoleMenuWriteManage;
import com.egeo.components.user.po.RoleMenuPO;
import com.egeo.components.user.service.write.RoleMenuWriteService;


@Service("roleMenuWriteService")
public class RoleMenuWriteServiceImpl implements RoleMenuWriteService {
	@Autowired
	private RoleMenuWriteManage roleMenuWriteManage;
	
	 public String saveWithTx(RoleMenuDTO dto) {
		return roleMenuWriteManage.saveWithTx(RoleMenuConverter.toPO(dto));
	 }

	@Override
	public String refreshWithTx(List<RoleMenuDTO> dtos, Long roleId) {
		// TODO Auto-generated method stub
		RoleMenuPO po = new RoleMenuPO();
		po.setRoleId(roleId);
		if(dtos!=null && roleId!=null && dtos.size()>0) {

			roleMenuWriteManage.delete(po);
			for(RoleMenuDTO dto:dtos) {
				if(dto.getMenuId()!=null &&dto.getMenuId()>0 ) {
					if(dto.getRoleId()!=null &&dto.getRoleId()>0 && dto.getRoleId().equals(roleId) ) {
						dto.setCreateTime(new Date());
						saveWithTx(dto);
					}
				}
			}
		}
		return null;
	}
}
	