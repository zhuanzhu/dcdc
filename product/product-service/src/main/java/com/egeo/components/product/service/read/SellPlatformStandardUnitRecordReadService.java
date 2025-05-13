package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.SellPlatformStandardUnitRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface SellPlatformStandardUnitRecordReadService {

	public SellPlatformStandardUnitRecordDTO findSellPlatformStandardUnitRecordById(SellPlatformStandardUnitRecordDTO dto);

	public PageResult<SellPlatformStandardUnitRecordDTO> findSellPlatformStandardUnitRecordOfPage(SellPlatformStandardUnitRecordDTO dto,Pagination page);

	public List<SellPlatformStandardUnitRecordDTO> findSellPlatformStandardUnitRecordAll(SellPlatformStandardUnitRecordDTO dto);
}
	