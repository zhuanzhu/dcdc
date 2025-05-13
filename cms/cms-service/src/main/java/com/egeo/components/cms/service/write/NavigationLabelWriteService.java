package com.egeo.components.cms.service.write;

import com.egeo.components.cms.dto.LinkableButtonDTO;
import com.egeo.components.cms.dto.NavigationLabelDTO;


public interface NavigationLabelWriteService {

	public Long insertNavigationLabelWithTx(NavigationLabelDTO dto);

	public int updateNavigationLabelWithTx(NavigationLabelDTO dto);

	public int deleteNavigationLabelWithTx(NavigationLabelDTO dto);

	/**
	 * 新增或编辑导航栏标签
	 * @param dto
	 * @param linkableButtonDTO
	 * @return
	 */
	public Long insertOrUpdateNavigationLableWithTx(NavigationLabelDTO dto, LinkableButtonDTO linkableButtonDTO);
}
	