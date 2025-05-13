package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.StandardUnitTagWriteService;
import com.egeo.components.product.manage.write.StandardUnitTagWriteManage;
import com.egeo.components.product.converter.StandardUnitTagConverter;
import com.egeo.components.product.dto.StandardUnitTagDTO;
import com.egeo.components.product.po.StandardUnitTagPO;

@Service("standardUnitTagWriteService")
public class StandardUnitTagWriteServiceImpl  implements StandardUnitTagWriteService {
	@Autowired
	private StandardUnitTagWriteManage standardUnitTagWriteManage;

	@Override
	public Long insertStandardUnitTagWithTx(StandardUnitTagDTO dto) {
		StandardUnitTagPO po = StandardUnitTagConverter.toPO(dto);
		Long rt = standardUnitTagWriteManage.insertStandardUnitTagWithTx(po);		
		return rt;
	}

	@Override
	public int updateStandardUnitTagWithTx(StandardUnitTagDTO dto) {
		StandardUnitTagPO po = StandardUnitTagConverter.toPO(dto);
		int rt = standardUnitTagWriteManage.updateStandardUnitTagWithTx(po);		
		return rt;
	}

	@Override
	public int deleteStandardUnitTagWithTx(StandardUnitTagDTO dto) {
		StandardUnitTagPO po = StandardUnitTagConverter.toPO(dto);
		int rt = standardUnitTagWriteManage.deleteStandardUnitTagWithTx(po);		
		return rt;
	}
	/**
	 * 根据标签id清除记录
	 * @param tagId
	 * @return
	 */
	@Override
	public int delByTagId(Long tagId) {
		// TODO Auto-generated method stub
		return standardUnitTagWriteManage.delByTagId(tagId);
	}

	@Override
	public int delSuRelationBysuIdWithTx(Long standardUnitId) {
		return standardUnitTagWriteManage.delByStandardUnitId(standardUnitId);
	}
}
	