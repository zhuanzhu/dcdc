package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.TagWriteService;
import com.egeo.components.product.manage.write.TagWriteManage;
import com.egeo.components.product.converter.TagConverter;
import com.egeo.components.product.dto.TagDTO;
import com.egeo.components.product.po.TagPO;

@Service("tagWriteService")
public class TagWriteServiceImpl  implements TagWriteService {
	@Autowired
	private TagWriteManage tagWriteManage;

	@Override
	public Long insertTagWithTx(TagDTO dto) {
		TagPO po = TagConverter.toPO(dto);
		Long rt = tagWriteManage.insertTagWithTx(po);		
		return rt;
	}

	@Override
	public int updateTagWithTx(TagDTO dto) {
		TagPO po = TagConverter.toPO(dto);
		int rt = tagWriteManage.updateTagWithTx(po);		
		return rt;
	}

	@Override
	public int deleteTagWithTx(TagDTO dto) {
		TagPO po = TagConverter.toPO(dto);
		int rt = tagWriteManage.deleteTagWithTx(po);		
		return rt;
	}
	/**
	 * 根据标签id清除记录
	 * @param tagId
	 * @return
	 */
	@Override
	public Boolean tagClearRecordByTagIdWithTx(Long tagId) {
		// TODO Auto-generated method stub
		return tagWriteManage.tagClearRecordByTagIdWithTx(tagId);
	}
}
	