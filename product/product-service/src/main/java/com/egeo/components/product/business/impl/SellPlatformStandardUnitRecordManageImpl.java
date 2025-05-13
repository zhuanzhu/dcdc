package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.SellPlatformStandardUnitRecordManage;
import com.egeo.components.product.facade.SellPlatformStandardUnitRecordFacade;
import com.egeo.components.product.dto.SellPlatformStandardUnitRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("sellPlatformStandardUnitRecord")
public class SellPlatformStandardUnitRecordManageImpl implements SellPlatformStandardUnitRecordManage{

	
	@Resource(name="sellPlatformStandardUnitRecordFacade")
	private SellPlatformStandardUnitRecordFacade sellPlatformStandardUnitRecordFacade;

	@Override
	public SellPlatformStandardUnitRecordDTO findSellPlatformStandardUnitRecordById(SellPlatformStandardUnitRecordDTO dto) {
		return sellPlatformStandardUnitRecordFacade.findSellPlatformStandardUnitRecordById(dto);
	}

	@Override
	public PageResult<SellPlatformStandardUnitRecordDTO> findSellPlatformStandardUnitRecordOfPage(SellPlatformStandardUnitRecordDTO dto, Pagination page) {
		return sellPlatformStandardUnitRecordFacade.findSellPlatformStandardUnitRecordOfPage(dto, page);
	}

	@Override
	public List<SellPlatformStandardUnitRecordDTO> findSellPlatformStandardUnitRecordAll(SellPlatformStandardUnitRecordDTO dto) {
		return sellPlatformStandardUnitRecordFacade.findSellPlatformStandardUnitRecordAll(dto);
	}

	@Override
	public Long insertSellPlatformStandardUnitRecordWithTx(SellPlatformStandardUnitRecordDTO dto) {
		return sellPlatformStandardUnitRecordFacade.insertSellPlatformStandardUnitRecordWithTx(dto);
	}

	@Override
	public int updateSellPlatformStandardUnitRecordWithTx(SellPlatformStandardUnitRecordDTO dto) {
		return sellPlatformStandardUnitRecordFacade.updateSellPlatformStandardUnitRecordWithTx(dto);
	}

	@Override
	public int deleteSellPlatformStandardUnitRecordWithTx(SellPlatformStandardUnitRecordDTO dto) {
		return sellPlatformStandardUnitRecordFacade.deleteSellPlatformStandardUnitRecordWithTx(dto);
	}


}
	