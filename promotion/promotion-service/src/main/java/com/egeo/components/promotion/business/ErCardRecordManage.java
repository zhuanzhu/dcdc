package com.egeo.components.promotion.business;

import java.util.List;

import com.egeo.components.promotion.dto.ErCardRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ErCardRecordManage {

	public ErCardRecordDTO findErCardRecordById(ErCardRecordDTO dto);	

	public PageResult<ErCardRecordDTO> findErCardRecordOfPage(ErCardRecordDTO dto,Pagination page);

	public List<ErCardRecordDTO> findErCardRecordAll(ErCardRecordDTO dto);

	Long insertErCardRecordWithTx(ErCardRecordDTO dto);

	int updateErCardRecordWithTx(ErCardRecordDTO dto);

	int deleteErCardRecordWithTx(ErCardRecordDTO dto);
	/**
	 * 确认导入
	 * @return
	 */
	public int confirmTheImport(ErCardRecordDTO dto,Integer addStock);
}
	