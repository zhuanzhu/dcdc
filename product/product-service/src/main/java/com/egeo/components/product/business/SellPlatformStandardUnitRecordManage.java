package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.SellPlatformStandardUnitRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SellPlatformStandardUnitRecordManage {

	public SellPlatformStandardUnitRecordDTO findSellPlatformStandardUnitRecordById(SellPlatformStandardUnitRecordDTO dto);	

	public PageResult<SellPlatformStandardUnitRecordDTO> findSellPlatformStandardUnitRecordOfPage(SellPlatformStandardUnitRecordDTO dto,Pagination page);

	public List<SellPlatformStandardUnitRecordDTO> findSellPlatformStandardUnitRecordAll(SellPlatformStandardUnitRecordDTO dto);

	Long insertSellPlatformStandardUnitRecordWithTx(SellPlatformStandardUnitRecordDTO dto);

	int updateSellPlatformStandardUnitRecordWithTx(SellPlatformStandardUnitRecordDTO dto);

	int deleteSellPlatformStandardUnitRecordWithTx(SellPlatformStandardUnitRecordDTO dto);
}
	