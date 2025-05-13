package com.egeo.components.config.service.read;


import java.util.List;

import com.egeo.components.config.dto.SaltDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface SaltReadService {

	public SaltDTO findSaltById(SaltDTO dto);

	public PageResult<SaltDTO> findSaltOfPage(SaltDTO dto,Pagination page);

	public List<SaltDTO> findSaltAll(SaltDTO dto);

	/**
	 * 根据uuid获取盐对象
	 * @param uuid
	 * @return
	 */
	public SaltDTO querySaltByUUID(String uuid);
}
	