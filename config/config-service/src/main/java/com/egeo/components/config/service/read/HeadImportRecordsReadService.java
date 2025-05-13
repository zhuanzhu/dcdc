package com.egeo.components.config.service.read;


import java.util.List;

import com.egeo.components.config.dto.HeadImportRecordsDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface HeadImportRecordsReadService {

	public HeadImportRecordsDTO findHeadImportRecordsById(HeadImportRecordsDTO dto);

	public PageResult<HeadImportRecordsDTO> findHeadImportRecordsOfPage(HeadImportRecordsDTO dto,Pagination page);

	public List<HeadImportRecordsDTO> findHeadImportRecordsAll(HeadImportRecordsDTO dto);

	/**
	 * 根据sn查询正式导入记录(似乎是不安全的  too many results)
	 * @param sn
	 * @return
	 */
	public HeadImportRecordsDTO queryRecordBySn(String sn);

	/**
	 * 根据sn查询头记录列表
	 * @param sn
	 * @return
	 */
	public List<HeadImportRecordsDTO> queryRecordsBySn(String sn);
}
	