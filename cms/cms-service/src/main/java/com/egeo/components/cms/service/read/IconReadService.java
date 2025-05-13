package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.IconDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface IconReadService {

	public IconDTO findIconById(IconDTO dto);

	public PageResult<IconDTO> findIconOfPage(IconDTO dto,Pagination page);

	public List<IconDTO> findIconAll(IconDTO dto);

	/**
	 * 根据组id查询icon列表
	 * @param groupId
	 * @return
	 */
	public List<IconDTO> queryIconsByGroupId(Long groupId);
}
	