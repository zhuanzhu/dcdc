package com.egeo.components.config.business;

import java.util.List;

import com.egeo.components.config.dto.PolymallUserDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface PolymallUserManage {

	public PolymallUserDTO findPolymallUserById(PolymallUserDTO dto);	

	public PageResult<PolymallUserDTO> findPolymallUserOfPage(PolymallUserDTO dto,Pagination page);

	public List<PolymallUserDTO> findPolymallUserAll(PolymallUserDTO dto);

	Long insertPolymallUserWithTx(PolymallUserDTO dto);

	int updatePolymallUserWithTx(PolymallUserDTO dto);

	int deletePolymallUserWithTx(PolymallUserDTO dto);
}
	