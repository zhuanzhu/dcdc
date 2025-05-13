package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.StandardUnitCompanyRecordWriteService;
import com.egeo.components.product.manage.write.StandardUnitCompanyRecordWriteManage;
import com.egeo.components.product.converter.StandardUnitCompanyRecordConverter;
import com.egeo.components.product.dto.StandardUnitCompanyRecordDTO;
import com.egeo.components.product.po.StandardUnitCompanyRecordPO;

@Service("standardUnitCompanyRecordWriteService")
public class StandardUnitCompanyRecordWriteServiceImpl  implements StandardUnitCompanyRecordWriteService {
	@Autowired
	private StandardUnitCompanyRecordWriteManage standardUnitCompanyRecordWriteManage;

	@Override
	public Long insertStandardUnitCompanyRecordWithTx(StandardUnitCompanyRecordDTO dto) {
		StandardUnitCompanyRecordPO po = StandardUnitCompanyRecordConverter.toPO(dto);
		Long rt = standardUnitCompanyRecordWriteManage.insertStandardUnitCompanyRecordWithTx(po);		
		return rt;
	}

	@Override
	public int updateStandardUnitCompanyRecordWithTx(StandardUnitCompanyRecordDTO dto) {
		StandardUnitCompanyRecordPO po = StandardUnitCompanyRecordConverter.toPO(dto);
		int rt = standardUnitCompanyRecordWriteManage.updateStandardUnitCompanyRecordWithTx(po);		
		return rt;
	}

	@Override
	public int deleteStandardUnitCompanyRecordWithTx(StandardUnitCompanyRecordDTO dto) {
		StandardUnitCompanyRecordPO po = StandardUnitCompanyRecordConverter.toPO(dto);
		int rt = standardUnitCompanyRecordWriteManage.deleteStandardUnitCompanyRecordWithTx(po);		
		return rt;
	}
}
	