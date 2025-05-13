package com.egeo.components.user.facade;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.user.dto.PlatformRole;
import com.egeo.components.user.dto.RoleDTO;
import com.egeo.components.user.dto.RoleFunctionTreeNodeDTO;
import com.egeo.components.user.dto.RoleMenuDTO;
import com.egeo.components.user.dto.RoleUrlDTO;
import com.egeo.components.user.service.read.FunctionTreeNodeReadService;
import com.egeo.components.user.service.read.MenuReadService;
import com.egeo.components.user.service.read.RoleFunctionTreeNodeReadService;
import com.egeo.components.user.service.read.RoleMenuReadService;
import com.egeo.components.user.service.read.RoleReadService;
import com.egeo.components.user.service.read.RoleUrlReadService;
import com.egeo.components.user.service.write.RoleWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;


@Component
public class RoleFacade {
	
	@Resource
	private RoleReadService roleReadService;
	@Resource
	private RoleWriteService rolesWriteService;
	@Resource
	private RoleMenuReadService roleMenuReadService;
	@Resource
	private RoleUrlReadService roleUrlReadService;
	@Resource
	private RoleFunctionTreeNodeReadService roleFunctionTreeNodeReadService;
	@Resource
	private FunctionTreeNodeReadService functionTreeNodeReadService;
	
	
	@Resource
	private MenuReadService menuReadService;

	public List<RoleDTO> findRoleListByUserId(Long id) {
		
		return roleReadService.findRoleListByUserId(id);
	}

	

	public Long saveRoleWithTx(RoleDTO roleDTO) {
		
		return rolesWriteService.saveRoleWithTx(roleDTO);
	}



	public int removeRoleWithTx(String  name) {

		return rolesWriteService.removeRoleWithTx(name);
	}






	public int updateRoleWithTx(RoleDTO dto) {
		
		return rolesWriteService.updateRoleWithTx(dto);
	}



	public RoleDTO findRoleByRoleName(String name) {
		
		return roleReadService.findRoleByRoleName(name);
	}

	public RoleDTO findRoleByRoleNamePlatformId(String name,Long platformId) {

		return roleReadService.findRoleByRoleNamePlatformId(name,platformId);
	}


	public List<RoleDTO> findAll(RoleDTO dto) {
		
		return roleReadService.findAll(dto);
	}



    public int upDateUserWithTx(RoleDTO roleDTO, String userName) {
        
        return rolesWriteService.upDateUserWithTx(roleDTO,userName);
    }



    public List<PlatformRole> roleByPlatformId(String platformId,Long id) {
        
        return roleReadService.roleByPlatformIdWithTx(platformId,id);
    }



    public int upDateRoleWithTx(String menus, Long roleId) {
        
        return rolesWriteService.upDateRoleWithTx(menus, roleId);
    }



    public PageResult<RoleDTO> roleListPage(Pagination page, RoleDTO dto) {
        
        return roleReadService.roleListPage(page, dto);
    }

    
    
    public RoleDTO findById(RoleDTO roleDTO) {
    	RoleDTO result = roleReadService.findById(roleDTO);
    	findRoleMenu(result);
    	findRoleUrl(result);
    	findRoleFunctionTreeNode(result);
        return result;
    }

    private void findRoleMenu(RoleDTO roleDTO) {
    	RoleMenuDTO condition = new RoleMenuDTO();
    	condition.setRoleId(roleDTO.getId());
    	List<RoleMenuDTO> dtoList = roleMenuReadService.findAll(condition);
    	if (EmptyUtil.isNotEmpty(dtoList)) {
    		List<Long> idList = new ArrayList<>();
    		for (RoleMenuDTO dto : dtoList) {
    			idList.add(dto.getMenuId());
    		}
    		roleDTO.setMenuIdList(idList);
    	}
    }
    private void findRoleUrl(RoleDTO roleDTO) {
    	RoleUrlDTO condition = new RoleUrlDTO();
    	condition.setRoleId(roleDTO.getId());
    	List<RoleUrlDTO> dtoList = roleUrlReadService.findAll(condition);
    	if (EmptyUtil.isNotEmpty(dtoList)) {
    		List<Long> idList = new ArrayList<>();
    		for (RoleUrlDTO dto : dtoList) {
    			idList.add(dto.getUrlId());
    		}
    		roleDTO.setUrlIdList(idList);
    	}
    }
    private void findRoleFunctionTreeNode(RoleDTO roleDTO) {
    	RoleFunctionTreeNodeDTO condition = new RoleFunctionTreeNodeDTO();
    	condition.setRoleId(roleDTO.getId());
    	List<RoleFunctionTreeNodeDTO> dtoList = roleFunctionTreeNodeReadService.findRoleFunctionTreeNodeAll(condition);
    	if (EmptyUtil.isNotEmpty(dtoList)) {
    		List<Long> idList = new ArrayList<>();
    		for (RoleFunctionTreeNodeDTO dto : dtoList) {
    			idList.add(dto.getFunctionTreeNodeId());
    		}
    		roleDTO.setFunctionIdList(idList);
    	}
    }
    
    

    public int deleteByIdWithTx(RoleDTO roleDto) {
        
        return rolesWriteService.deleteByIdWithTx(roleDto);
    }

	public void updateRoleStatus(RoleDTO roleDto, Integer useable) {
		rolesWriteService.updateRoleStatus(roleDto,useable);
	}
}
	