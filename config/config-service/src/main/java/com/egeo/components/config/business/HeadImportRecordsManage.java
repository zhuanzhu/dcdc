package com.egeo.components.config.business;

import java.util.List;

import com.egeo.components.config.dto.HeadImportRecordsDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface HeadImportRecordsManage {

	public HeadImportRecordsDTO findHeadImportRecordsById(HeadImportRecordsDTO dto);	

	public PageResult<HeadImportRecordsDTO> findHeadImportRecordsOfPage(HeadImportRecordsDTO dto,Pagination page);

	public List<HeadImportRecordsDTO> findHeadImportRecordsAll(HeadImportRecordsDTO dto);

	Long insertHeadImportRecordsWithTx(HeadImportRecordsDTO dto);

	int updateHeadImportRecordsWithTx(HeadImportRecordsDTO dto);

	int deleteHeadImportRecordsWithTx(HeadImportRecordsDTO dto);
}
	