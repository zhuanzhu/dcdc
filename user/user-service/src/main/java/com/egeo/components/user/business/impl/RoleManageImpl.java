package com.egeo.components.user.business.impl;
	

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.user.business.RoleManage;
import com.egeo.components.user.converter.RoleConverter;
import com.egeo.components.user.dto.PlatformRole;
import com.egeo.components.user.dto.RoleDTO;
import com.egeo.components.user.facade.RoleFacade;
import com.egeo.components.user.vo.RoleVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service("role")
public class RoleManageImpl implements RoleManage {

	
	@Resource(name="roleFacade")
	private RoleFacade roleFacade;


	@Override
	public List<RoleDTO> findRoleListByUserId(Long id) {
		
		return roleFacade.findRoleListByUserId(id);
	}

	@Override
	public Long saveRoleWithTx(RoleDTO role) {
       return roleFacade.saveRoleWithTx(role);
	}

	public int updateRoleWithTx(RoleDTO role) {
		return roleFacade.updateRoleWithTx(role);
	}

    @Override
    public void updateRoleStatus(RoleDTO roleDto, Integer useable) {
        roleFacade.updateRoleStatus(roleDto,useable);
    }


    @Override
	public int removeRoleWithTx(String  name) {
		
		return roleFacade.removeRoleWithTx(name);
	}





	@Override
	public RoleDTO findRoleByRoleName(String name) {
		
		return roleFacade.findRoleByRoleName(name);
	}

    @Override
    public RoleDTO findRoleByRoleNamePlatformId(String name,Long platformId) {

        return roleFacade.findRoleByRoleNamePlatformId(name,platformId);
    }

	@Override
	public List<RoleDTO> findAll(RoleDTO dto) {
		
		return roleFacade.findAll(dto);
	}

    @Override
    public int upDateUserWithTx(RoleDTO roleDTO, String userName) {
        
        return roleFacade.upDateUserWithTx(roleDTO,userName);
    }

    @Override
    public List<PlatformRole> roleByPlatformId(String platformId,Long id) {
        
        return roleFacade.roleByPlatformId(platformId,id);
    }

    @Override
    public int upDateRoleWithTx(String menus, Long roleId) {
        return roleFacade.upDateRoleWithTx(menus, roleId);
    }

    @Override
    public PageResult<RoleDTO> roleListPage(Pagination page, RoleVO roleVO) {
        
        return roleFacade.roleListPage(page, RoleConverter.toDTO(roleVO));
    }

    @Override
    public RoleDTO findById(RoleDTO roleDTO) {

        return roleFacade.findById(roleDTO);
    }

    @Override
    public int deleteByIdWithTx(RoleDTO roleDto) {
        
        return roleFacade.deleteByIdWithTx(roleDto);
    }
	
	
}
	