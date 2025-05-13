package com.egeo.components.user.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.user.business.RoleFunctionTreeNodeManage;
import com.egeo.components.user.facade.RoleFunctionTreeNodeFacade;
import com.egeo.components.user.dto.RoleFunctionTreeNodeDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("roleFunctionTreeNode")
public class RoleFunctionTreeNodeManageImpl implements RoleFunctionTreeNodeManage{

	
	@Resource(name="roleFunctionTreeNodeFacade")
	private RoleFunctionTreeNodeFacade roleFunctionTreeNodeFacade;

	@Override
	public RoleFunctionTreeNodeDTO findRoleFunctionTreeNodeById(RoleFunctionTreeNodeDTO dto) {
		return roleFunctionTreeNodeFacade.findRoleFunctionTreeNodeById(dto);
	}

	@Override
	public PageResult<RoleFunctionTreeNodeDTO> findRoleFunctionTreeNodeOfPage(RoleFunctionTreeNodeDTO dto, Pagination page) {
		return roleFunctionTreeNodeFacade.findRoleFunctionTreeNodeOfPage(dto, page);
	}

	@Override
	public List<RoleFunctionTreeNodeDTO> findRoleFunctionTreeNodeAll(RoleFunctionTreeNodeDTO dto) {
		return roleFunctionTreeNodeFacade.findRoleFunctionTreeNodeAll(dto);
	}

	@Override
	public Long insertRoleFunctionTreeNodeWithTx(RoleFunctionTreeNodeDTO dto) {
		return roleFunctionTreeNodeFacade.insertRoleFunctionTreeNodeWithTx(dto);
	}

	@Override
	public int updateRoleFunctionTreeNodeWithTx(RoleFunctionTreeNodeDTO dto) {
		return roleFunctionTreeNodeFacade.updateRoleFunctionTreeNodeWithTx(dto);
	}

	@Override
	public int deleteRoleFunctionTreeNodeWithTx(RoleFunctionTreeNodeDTO dto) {
		return roleFunctionTreeNodeFacade.deleteRoleFunctionTreeNodeWithTx(dto);
	}


}
	