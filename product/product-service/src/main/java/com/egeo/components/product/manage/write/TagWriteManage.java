package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.TagPO;


public interface TagWriteManage {

	Long insertTagWithTx(TagPO po);

	int updateTagWithTx(TagPO po);

	int deleteTagWithTx(TagPO po);
	/**
	 * 根据标签id清除记录
	 * @param tagId
	 * @return
	 */
	Boolean tagClearRecordByTagIdWithTx(Long tagId);
}
	