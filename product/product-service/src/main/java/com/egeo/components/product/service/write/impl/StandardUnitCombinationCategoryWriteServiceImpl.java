package com.egeo.components.product.service.write.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.StandardUnitCombinationCategoryWriteService;
import com.egeo.components.product.manage.write.StandardUnitCombinationCategoryWriteManage;
import com.egeo.components.product.converter.StandardUnitCombinationCategoryConverter;
import com.egeo.components.product.converter.StandardUnitCombinationConverter;
import com.egeo.components.product.dto.StandardUnitCombinationCategoryDTO;
import com.egeo.components.product.dto.StandardUnitCombinationDTO;
import com.egeo.components.product.po.StandardUnitCombinationCategoryPO;

@Service("standardUnitCombinationCategoryWriteService")
public class StandardUnitCombinationCategoryWriteServiceImpl  implements StandardUnitCombinationCategoryWriteService {
	@Autowired
	private StandardUnitCombinationCategoryWriteManage standardUnitCombinationCategoryWriteManage;

	@Override
	public Long insertStandardUnitCombinationCategoryWithTx(StandardUnitCombinationCategoryDTO dto) {
		StandardUnitCombinationCategoryPO po = StandardUnitCombinationCategoryConverter.toPO(dto);
		Long rt = standardUnitCombinationCategoryWriteManage.insertStandardUnitCombinationCategoryWithTx(po);		
		return rt;
	}

	@Override
	public int updateStandardUnitCombinationCategoryWithTx(StandardUnitCombinationCategoryDTO dto) {
		StandardUnitCombinationCategoryPO po = StandardUnitCombinationCategoryConverter.toPO(dto);
		int rt = standardUnitCombinationCategoryWriteManage.updateStandardUnitCombinationCategoryWithTx(po);		
		return rt;
	}

	@Override
	public int deleteStandardUnitCombinationCategoryWithTx(StandardUnitCombinationCategoryDTO dto) {
		StandardUnitCombinationCategoryPO po = StandardUnitCombinationCategoryConverter.toPO(dto);
		int rt = standardUnitCombinationCategoryWriteManage.deleteStandardUnitCombinationCategoryWithTx(po);		
		return rt;
	}

	/**
	 * 批量保存su组合和前台类目节点关系
	 * @param sUCombinationDTO
	 * @param categoryTreeNodeIdList
	 * @return
	 */
	@Override
	public boolean saveStandardUnitCombinationCategoryAllWithTx(StandardUnitCombinationDTO sUCombinationDTO,
																List<Long> categoryTreeNodeIdList) {
		return standardUnitCombinationCategoryWriteManage.saveStandardUnitCombinationCategoryAllWithTx(
				StandardUnitCombinationConverter.toPO(sUCombinationDTO), categoryTreeNodeIdList);
	}
}
	