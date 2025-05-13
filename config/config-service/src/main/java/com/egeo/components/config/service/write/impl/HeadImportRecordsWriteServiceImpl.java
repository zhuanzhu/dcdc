package com.egeo.components.config.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.config.converter.HeadImportRecordsConverter;
import com.egeo.components.config.dto.HeadImportRecordsDTO;
import com.egeo.components.config.manage.write.HeadImportRecordsWriteManage;
import com.egeo.components.config.po.HeadImportRecordsPO;
import com.egeo.components.config.service.write.HeadImportRecordsWriteService;

@Service("headImportRecordsWriteService")
public class HeadImportRecordsWriteServiceImpl implements HeadImportRecordsWriteService {
	@Autowired
	private HeadImportRecordsWriteManage headImportRecordsWriteManage;

	@Override
	public Long insertHeadImportRecordsWithTx(HeadImportRecordsDTO dto) {
		HeadImportRecordsPO po = HeadImportRecordsConverter.toPO(dto);
		Long rt = headImportRecordsWriteManage.insertHeadImportRecordsWithTx(po);		
		return rt;
	}

	@Override
	public int updateHeadImportRecordsWithTx(HeadImportRecordsDTO dto) {
		HeadImportRecordsPO po = HeadImportRecordsConverter.toPO(dto);
		int rt = headImportRecordsWriteManage.updateHeadImportRecordsWithTx(po);		
		return rt;
	}

	@Override
	public int deleteHeadImportRecordsWithTx(HeadImportRecordsDTO dto) {
		HeadImportRecordsPO po = HeadImportRecordsConverter.toPO(dto);
		int rt = headImportRecordsWriteManage.deleteHeadImportRecordsWithTx(po);		
		return rt;
	}
}
	