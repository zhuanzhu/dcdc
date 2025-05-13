package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.IconGroupDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface IconGroupReadService {

	public IconGroupDTO findIconGroupById(IconGroupDTO dto);

	public PageResult<IconGroupDTO> findIconGroupOfPage(IconGroupDTO dto,Pagination page);

	public List<IconGroupDTO> findIconGroupAll(IconGroupDTO dto);

	/**
	 * 根据实例id查询icon组
	 * @param instId
	 * @return
	 */
	public IconGroupDTO queryIconGroupByInstId(Long instId);
}
	