package com.egeo.components.cms.service.read;


import java.util.List;

import com.egeo.components.cms.dto.InstDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface InstReadService {

	public InstDTO findInstById(Long id);

	public PageResult<InstDTO> findInstOfPage(InstDTO dto,Pagination page);

	public List<InstDTO> findInstAll(InstDTO dto);

	/**
	 * 根据组件id拆查询实例
	 * @param elementId
	 * @return
	 */
	public InstDTO queryInstByElementId(Long elementId);
}
	