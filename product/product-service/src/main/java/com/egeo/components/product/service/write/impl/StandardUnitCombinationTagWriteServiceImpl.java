package com.egeo.components.product.service.write.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.write.StandardUnitCombinationTagWriteService;
import com.egeo.components.product.manage.write.StandardUnitCombinationTagWriteManage;
import com.egeo.components.product.converter.StandardUnitCombinationConverter;
import com.egeo.components.product.converter.StandardUnitCombinationTagConverter;
import com.egeo.components.product.dto.StandardUnitCombinationDTO;
import com.egeo.components.product.dto.StandardUnitCombinationTagDTO;
import com.egeo.components.product.po.StandardUnitCombinationTagPO;


@Service("standardUnitCombinationTagWriteService")
public class StandardUnitCombinationTagWriteServiceImpl  implements StandardUnitCombinationTagWriteService {
	@Autowired
	private StandardUnitCombinationTagWriteManage standardUnitCombinationTagWriteManage;

	@Override
	public Long insertStandardUnitCombinationTagWithTx(StandardUnitCombinationTagDTO dto) {
		StandardUnitCombinationTagPO po = StandardUnitCombinationTagConverter.toPO(dto);
		Long rt = standardUnitCombinationTagWriteManage.insertStandardUnitCombinationTagWithTx(po);		
		return rt;
	}

	@Override
	public int updateStandardUnitCombinationTagWithTx(StandardUnitCombinationTagDTO dto) {
		StandardUnitCombinationTagPO po = StandardUnitCombinationTagConverter.toPO(dto);
		int rt = standardUnitCombinationTagWriteManage.updateStandardUnitCombinationTagWithTx(po);		
		return rt;
	}

	@Override
	public int deleteStandardUnitCombinationTagWithTx(StandardUnitCombinationTagDTO dto) {
		StandardUnitCombinationTagPO po = StandardUnitCombinationTagConverter.toPO(dto);
		int rt = standardUnitCombinationTagWriteManage.deleteStandardUnitCombinationTagWithTx(po);		
		return rt;
	}
	/**
	 * 批量保存su组合id与标签关系信息
	 * @param standardUnitCombinationId
	 * @return
	 */
	@Override
	public Integer saveStandardUnitCombinationTagWithTx(StandardUnitCombinationDTO standardUnitCombinationDTO,
			List<Long> tagIdList) {
		// TODO Auto-generated method stub
		return standardUnitCombinationTagWriteManage.saveStandardUnitCombinationTagWithTx(StandardUnitCombinationConverter.toPO(standardUnitCombinationDTO),tagIdList);
	}
	/**
	 * 根据标签id清除记录
	 * @param tagId
	 * @return
	 */
	@Override
	public int delByTagId(Long tagId) {
		// TODO Auto-generated method stub
		return standardUnitCombinationTagWriteManage.delByTagId(tagId);
	}
}
	