package com.egeo.components.promotion.service.write;

import com.egeo.components.promotion.dto.ErCardRecordDTO;


public interface ErCardRecordWriteService {

	public Long insertErCardRecordWithTx(ErCardRecordDTO dto);

	public int updateErCardRecordWithTx(ErCardRecordDTO dto);

	public int deleteErCardRecordWithTx(ErCardRecordDTO dto);
	/**
	 * 根据记录id删除unit
	 * @param dto
	 * @return
	 */
	public int findErCardRecordByImportRecordsId(ErCardRecordDTO dto);
}
	