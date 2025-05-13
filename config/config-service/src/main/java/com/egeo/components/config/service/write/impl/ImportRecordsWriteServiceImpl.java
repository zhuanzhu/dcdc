package com.egeo.components.config.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.config.converter.ImportRecordsConverter;
import com.egeo.components.config.dto.ImportRecordsDTO;
import com.egeo.components.config.manage.write.ImportRecordsWriteManage;
import com.egeo.components.config.po.ImportRecordsPO;
import com.egeo.components.config.service.write.ImportRecordsWriteService;

@Service("importRecordsWriteService")
public class ImportRecordsWriteServiceImpl implements ImportRecordsWriteService {
	@Autowired
	private ImportRecordsWriteManage importRecordsWriteManage;

	@Override
	public Long insertImportRecordsWithTx(ImportRecordsDTO dto) {
		ImportRecordsPO po = ImportRecordsConverter.toPO(dto);
		Long rt = importRecordsWriteManage.insertImportRecordsWithTx(po);		
		return rt;
	}

	@Override
	public int updateImportRecordsWithTx(ImportRecordsDTO dto) {
		ImportRecordsPO po = ImportRecordsConverter.toPO(dto);
		int rt = importRecordsWriteManage.updateImportRecordsWithTx(po);		
		return rt;
	}

	@Override
	public int deleteImportRecordsWithTx(ImportRecordsDTO dto) {
		ImportRecordsPO po = ImportRecordsConverter.toPO(dto);
		int rt = importRecordsWriteManage.deleteImportRecordsWithTx(po);		
		return rt;
	}

	@Override
	public int deleteBySn(String sn) {
		
		return importRecordsWriteManage.deleteBySnWithTx(sn);
	}

}
	