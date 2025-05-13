package com.egeo.components.config.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.config.converter.ImportRecordsConverter;
import com.egeo.components.config.dto.ImportRecordsDTO;
import com.egeo.components.config.manage.read.ImportRecordsReadManage;
import com.egeo.components.config.po.ImportRecordsPO;
import com.egeo.components.config.service.read.ImportRecordsReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("importRecordsReadService")
public class ImportRecordsReadServiceImpl implements ImportRecordsReadService {
	@Autowired
	private ImportRecordsReadManage importRecordsReadManage;

	@Override
	public ImportRecordsDTO findImportRecordsById(ImportRecordsDTO dto) {
		ImportRecordsPO po = ImportRecordsConverter.toPO(dto);
		ImportRecordsPO list = importRecordsReadManage.findImportRecordsById(po);		
		return ImportRecordsConverter.toDTO(list);
	}

	@Override
	public PageResult<ImportRecordsDTO> findImportRecordsOfPage(ImportRecordsDTO dto, Pagination page) {
		ImportRecordsPO po = ImportRecordsConverter.toPO(dto);
        PageResult<ImportRecordsPO> pageResult = importRecordsReadManage.findImportRecordsOfPage(po, page);
        
        List<ImportRecordsDTO> list = ImportRecordsConverter.toDTO(pageResult.getList());
        PageResult<ImportRecordsDTO> result = new PageResult<ImportRecordsDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<ImportRecordsDTO> findImportRecordsAll(ImportRecordsDTO dto) {
		ImportRecordsPO po = ImportRecordsConverter.toPO(dto);
		List<ImportRecordsPO> list = importRecordsReadManage.findImportRecordsAll(po);		
		return ImportRecordsConverter.toDTO(list);
	}

	@Override
	public ImportRecordsDTO queryRecordTempBySn(String sn) {
		
		return ImportRecordsConverter.toDTO(importRecordsReadManage.queryRecordTempBySn(sn));
	}

}
	