package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.StandardUnitDescribeRecordWriteService;
import com.egeo.components.product.manage.write.StandardUnitDescribeRecordWriteManage;
import com.egeo.components.product.converter.StandardUnitDescribeRecordConverter;
import com.egeo.components.product.dto.StandardUnitDescribeRecordDTO;
import com.egeo.components.product.po.StandardUnitDescribeRecordPO;

@Service("standardUnitDescribeRecordWriteService")
public class StandardUnitDescribeRecordWriteServiceImpl  implements StandardUnitDescribeRecordWriteService {
	@Autowired
	private StandardUnitDescribeRecordWriteManage standardUnitDescribeRecordWriteManage;

	@Override
	public Long insertStandardUnitDescribeRecordWithTx(StandardUnitDescribeRecordDTO dto) {
		StandardUnitDescribeRecordPO po = StandardUnitDescribeRecordConverter.toPO(dto);
		Long rt = standardUnitDescribeRecordWriteManage.insertStandardUnitDescribeRecordWithTx(po);		
		return rt;
	}

	@Override
	public int updateStandardUnitDescribeRecordWithTx(StandardUnitDescribeRecordDTO dto) {
		StandardUnitDescribeRecordPO po = StandardUnitDescribeRecordConverter.toPO(dto);
		int rt = standardUnitDescribeRecordWriteManage.updateStandardUnitDescribeRecordWithTx(po);		
		return rt;
	}

	@Override
	public int deleteStandardUnitDescribeRecordWithTx(StandardUnitDescribeRecordDTO dto) {
		StandardUnitDescribeRecordPO po = StandardUnitDescribeRecordConverter.toPO(dto);
		int rt = standardUnitDescribeRecordWriteManage.deleteStandardUnitDescribeRecordWithTx(po);		
		return rt;
	}
}
	