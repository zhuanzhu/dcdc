package com.egeo.components.user.service.write.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.common.PlatformKeyConstant;
import com.egeo.components.user.converter.MenuConverter;
import com.egeo.components.user.dto.MenuDTO;
import com.egeo.components.user.dto.RoleDTO;
import com.egeo.components.user.dto.RoleMenuDTO;
import com.egeo.components.user.manage.read.MenuReadManage;
import com.egeo.components.user.manage.write.MenuWriteManage;
import com.egeo.components.user.po.MenuPO;
import com.egeo.components.user.service.read.RoleReadService;
import com.egeo.components.user.service.write.MenuWriteService;
import com.egeo.components.user.service.write.RoleMenuWriteService;


@Service("menuWriteService")
public class MenuWriteServiceImpl implements MenuWriteService {
	@Autowired
	private MenuWriteManage menuWriteManage;
	
	@Autowired
	private MenuReadManage menuReadManage;
	
	@Autowired
	private RoleReadService roleReadService;
	
	@Autowired
	private RoleMenuWriteService roleMenuWriteService;
	
	@Override
	public String saveOrUpdateWithTx(MenuDTO dto,String userName) {
		String poId = null;
		MenuPO menuPO = null;
		MenuPO po = MenuConverter.toPO(dto);
		if(dto.getId() != null){
		    menuPO = menuReadManage.getMenuId(dto.getId());
		}
		
		if(menuPO != null){
			poId = menuWriteManage.Update(po);
		}else{
		    if(po.getPlatformId() == null){
		        po.setPlatformId(7L);
		    }
		    if(po.getParentId() == null){
                po.setParentId(1L);
            }
		    
		    //通过平台id和管理员名字来确定各个管理员id
			RoleDTO roleDTO = new RoleDTO();
			roleDTO.setName(PlatformKeyConstant.PLATFORMIDADMINUSER);
			roleDTO.setPlatformId(po.getPlatformId());
			//管理员角色肯定唯一
			List<RoleDTO> roleList = roleReadService.findAll(roleDTO);

			Long menuId = menuWriteManage.save(po);

			return menuId==null?null:menuId+"";
			/*if(roleList.size() > 0){
				for (RoleDTO roleDTO2 : roleList) {
					//保存菜单和管理员角色的关系
					RoleMenuDTO roleMenuDTO = new RoleMenuDTO();
					roleMenuDTO.setMenuId(menuId);
					roleMenuDTO.setRoleId(roleDTO2.getId());
					roleMenuWriteService.saveWithTx(roleMenuDTO);
				}
			}*/
			
		}
		return null;
	}

	@Override
	public void deleteWithTx(MenuDTO dto) {
		MenuPO po = MenuConverter.toPO(dto);
		menuWriteManage.deleteWithTx(po);
		
	}
}
	