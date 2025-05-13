package com.egeo.components.config.service.read;


import java.util.List;

import com.egeo.components.config.dto.PolymallUserDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface PolymallUserReadService {

	public PolymallUserDTO findPolymallUserById(PolymallUserDTO dto);

	public PageResult<PolymallUserDTO> findPolymallUserOfPage(PolymallUserDTO dto,Pagination page);

	public List<PolymallUserDTO> findPolymallUserAll(PolymallUserDTO dto);
}
	