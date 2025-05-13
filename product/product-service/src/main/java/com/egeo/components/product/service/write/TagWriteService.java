package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.TagDTO;


public interface TagWriteService {

	public Long insertTagWithTx(TagDTO dto);

	public int updateTagWithTx(TagDTO dto);

	public int deleteTagWithTx(TagDTO dto);
	/**
	 * 根据标签id清除记录
	 * @param tagId
	 * @return
	 */
	public Boolean tagClearRecordByTagIdWithTx(Long tagId);
}
	