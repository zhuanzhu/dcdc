package com.egeo.components.cms.service.write;

import com.egeo.components.cms.dto.ElementDTO;


public interface ElementWriteService {

	public Long insertElementWithTx(ElementDTO dto);

	public int updateElementWithTx(ElementDTO dto);

	public int deleteElementWithTx(ElementDTO dto);

	/**
	 * 根据id删除组件
	 * @param elementId
	 * @return
	 */
	public int deleteElementById(Long elementId);
}
	