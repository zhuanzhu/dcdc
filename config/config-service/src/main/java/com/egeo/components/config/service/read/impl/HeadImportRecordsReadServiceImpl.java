package com.egeo.components.config.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.config.converter.HeadImportRecordsConverter;
import com.egeo.components.config.dto.HeadImportRecordsDTO;
import com.egeo.components.config.manage.read.HeadImportRecordsReadManage;
import com.egeo.components.config.po.HeadImportRecordsPO;
import com.egeo.components.config.service.read.HeadImportRecordsReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("headImportRecordsReadService")
public class HeadImportRecordsReadServiceImpl implements HeadImportRecordsReadService {
	@Autowired
	private HeadImportRecordsReadManage headImportRecordsReadManage;

	@Override
	public HeadImportRecordsDTO findHeadImportRecordsById(HeadImportRecordsDTO dto) {
		HeadImportRecordsPO po = HeadImportRecordsConverter.toPO(dto);
		HeadImportRecordsPO list = headImportRecordsReadManage.findHeadImportRecordsById(po);		
		return HeadImportRecordsConverter.toDTO(list);
	}

	@Override
	public PageResult<HeadImportRecordsDTO> findHeadImportRecordsOfPage(HeadImportRecordsDTO dto, Pagination page) {
		HeadImportRecordsPO po = HeadImportRecordsConverter.toPO(dto);
        PageResult<HeadImportRecordsPO> pageResult = headImportRecordsReadManage.findHeadImportRecordsOfPage(po, page);
        
        List<HeadImportRecordsDTO> list = HeadImportRecordsConverter.toDTO(pageResult.getList());
        PageResult<HeadImportRecordsDTO> result = new PageResult<HeadImportRecordsDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<HeadImportRecordsDTO> findHeadImportRecordsAll(HeadImportRecordsDTO dto) {
		HeadImportRecordsPO po = HeadImportRecordsConverter.toPO(dto);
		List<HeadImportRecordsPO> list = headImportRecordsReadManage.findHeadImportRecordsAll(po);		
		return HeadImportRecordsConverter.toDTO(list);
	}

	@Override
	public HeadImportRecordsDTO queryRecordBySn(String sn) {
		
		return HeadImportRecordsConverter.toDTO(headImportRecordsReadManage.queryRecordBySn(sn));
	}

	@Override
	public List<HeadImportRecordsDTO> queryRecordsBySn(String sn) {
		return HeadImportRecordsConverter.toDTO(headImportRecordsReadManage.queryRecordsBySn(sn));
	}
}
	