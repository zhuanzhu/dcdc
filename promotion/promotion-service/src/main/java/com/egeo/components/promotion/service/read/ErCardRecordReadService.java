package com.egeo.components.promotion.service.read;


import java.util.List;

import com.egeo.components.promotion.dto.ErCardRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface ErCardRecordReadService {

	public ErCardRecordDTO findErCardRecordById(ErCardRecordDTO dto);

	public PageResult<ErCardRecordDTO> findErCardRecordOfPage(ErCardRecordDTO dto,Pagination page);

	public List<ErCardRecordDTO> findErCardRecordAll(ErCardRecordDTO dto);
	/**
	 * 确认导入
	 * @return
	 */
	public List<ErCardRecordDTO> confirmTheImport(ErCardRecordDTO dto);
}
	