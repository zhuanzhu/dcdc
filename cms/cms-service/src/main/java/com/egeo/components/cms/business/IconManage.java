package com.egeo.components.cms.business;

import java.util.List;
	
import com.egeo.components.cms.dto.IconDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface IconManage {

	public IconDTO findIconById(IconDTO dto);	

	public PageResult<IconDTO> findIconOfPage(IconDTO dto,Pagination page);

	public List<IconDTO> findIconAll(IconDTO dto);

	Long insertIconWithTx(IconDTO dto);

	int updateIconWithTx(IconDTO dto);

	int deleteIconWithTx(IconDTO dto);
}
	