package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.StandardUnitCombinationWriteService;
import com.egeo.components.product.manage.write.StandardUnitCombinationWriteManage;
import com.egeo.components.product.converter.StandardUnitCombinationConverter;
import com.egeo.components.product.dto.StandardUnitCombinationDTO;
import com.egeo.components.product.po.StandardUnitCombinationPO;

@Service("standardUnitCombinationWriteService")
public class StandardUnitCombinationWriteServiceImpl  implements StandardUnitCombinationWriteService {
	@Autowired
	private StandardUnitCombinationWriteManage standardUnitCombinationWriteManage;

	@Override
	public Long insertStandardUnitCombinationWithTx(StandardUnitCombinationDTO dto) {
		StandardUnitCombinationPO po = StandardUnitCombinationConverter.toPO(dto);
		Long rt = standardUnitCombinationWriteManage.insertStandardUnitCombinationWithTx(po);		
		return rt;
	}

	@Override
	public int updateStandardUnitCombinationWithTx(StandardUnitCombinationDTO dto) {
		StandardUnitCombinationPO po = StandardUnitCombinationConverter.toPO(dto);
		int rt = standardUnitCombinationWriteManage.updateStandardUnitCombinationWithTx(po);		
		return rt;
	}

	@Override
	public int deleteStandardUnitCombinationWithTx(StandardUnitCombinationDTO dto) {
		StandardUnitCombinationPO po = StandardUnitCombinationConverter.toPO(dto);
		int rt = standardUnitCombinationWriteManage.deleteStandardUnitCombinationWithTx(po);		
		return rt;
	}
}
	