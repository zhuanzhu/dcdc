package com.egeo.components.config.business.impl;
	

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.config.business.HeadImportRecordsManage;
import com.egeo.components.config.dto.HeadImportRecordsDTO;
import com.egeo.components.config.facade.HeadImportRecordsFacade;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("headImportRecords")
public class HeadImportRecordsManageImpl implements HeadImportRecordsManage{

	
	@Resource(name="headImportRecordsFacade")
	private HeadImportRecordsFacade headImportRecordsFacade;

	@Override
	public HeadImportRecordsDTO findHeadImportRecordsById(HeadImportRecordsDTO dto) {
		return headImportRecordsFacade.findHeadImportRecordsById(dto);
	}

	@Override
	public PageResult<HeadImportRecordsDTO> findHeadImportRecordsOfPage(HeadImportRecordsDTO dto, Pagination page) {
		return headImportRecordsFacade.findHeadImportRecordsOfPage(dto, page);
	}

	@Override
	public List<HeadImportRecordsDTO> findHeadImportRecordsAll(HeadImportRecordsDTO dto) {
		return headImportRecordsFacade.findHeadImportRecordsAll(dto);
	}

	@Override
	public Long insertHeadImportRecordsWithTx(HeadImportRecordsDTO dto) {
		return headImportRecordsFacade.insertHeadImportRecordsWithTx(dto);
	}

	@Override
	public int updateHeadImportRecordsWithTx(HeadImportRecordsDTO dto) {
		return headImportRecordsFacade.updateHeadImportRecordsWithTx(dto);
	}

	@Override
	public int deleteHeadImportRecordsWithTx(HeadImportRecordsDTO dto) {
		return headImportRecordsFacade.deleteHeadImportRecordsWithTx(dto);
	}


}
	