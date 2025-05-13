package com.egeo.components.config.business.impl;
	

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.config.business.PolymallUserManage;
import com.egeo.components.config.dto.PolymallUserDTO;
import com.egeo.components.config.facade.PolymallUserFacade;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("polymallUser")
public class PolymallUserManageImpl implements PolymallUserManage{

	
	@Resource(name="polymallUserFacade")
	private PolymallUserFacade polymallUserFacade;

	@Override
	public PolymallUserDTO findPolymallUserById(PolymallUserDTO dto) {
		return polymallUserFacade.findPolymallUserById(dto);
	}

	@Override
	public PageResult<PolymallUserDTO> findPolymallUserOfPage(PolymallUserDTO dto, Pagination page) {
		return polymallUserFacade.findPolymallUserOfPage(dto, page);
	}

	@Override
	public List<PolymallUserDTO> findPolymallUserAll(PolymallUserDTO dto) {
		return polymallUserFacade.findPolymallUserAll(dto);
	}

	@Override
	public Long insertPolymallUserWithTx(PolymallUserDTO dto) {
		return polymallUserFacade.insertPolymallUserWithTx(dto);
	}

	@Override
	public int updatePolymallUserWithTx(PolymallUserDTO dto) {
		return polymallUserFacade.updatePolymallUserWithTx(dto);
	}

	@Override
	public int deletePolymallUserWithTx(PolymallUserDTO dto) {
		return polymallUserFacade.deletePolymallUserWithTx(dto);
	}


}
	