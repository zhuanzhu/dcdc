package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.SellPlatformStandardUnitManage;
import com.egeo.components.product.facade.SellPlatformStandardUnitFacade;
import com.egeo.components.product.dto.SellPlatformStandardUnitDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("sellPlatformStandardUnit")
public class SellPlatformStandardUnitManageImpl implements SellPlatformStandardUnitManage{

	
	@Resource(name="sellPlatformStandardUnitFacade")
	private SellPlatformStandardUnitFacade sellPlatformStandardUnitFacade;

	@Override
	public SellPlatformStandardUnitDTO findSellPlatformStandardUnitById(SellPlatformStandardUnitDTO dto) {
		return sellPlatformStandardUnitFacade.findSellPlatformStandardUnitById(dto);
	}

	@Override
	public PageResult<SellPlatformStandardUnitDTO> findSellPlatformStandardUnitOfPage(SellPlatformStandardUnitDTO dto, Pagination page) {
		return sellPlatformStandardUnitFacade.findSellPlatformStandardUnitOfPage(dto, page);
	}

	@Override
	public List<SellPlatformStandardUnitDTO> findSellPlatformStandardUnitAll(SellPlatformStandardUnitDTO dto) {
		return sellPlatformStandardUnitFacade.findSellPlatformStandardUnitAll(dto);
	}

	@Override
	public Long insertSellPlatformStandardUnitWithTx(SellPlatformStandardUnitDTO dto) {
		return sellPlatformStandardUnitFacade.insertSellPlatformStandardUnitWithTx(dto);
	}

	@Override
	public int updateSellPlatformStandardUnitWithTx(SellPlatformStandardUnitDTO dto) {
		return sellPlatformStandardUnitFacade.updateSellPlatformStandardUnitWithTx(dto);
	}

	@Override
	public int deleteSellPlatformStandardUnitWithTx(SellPlatformStandardUnitDTO dto) {
		return sellPlatformStandardUnitFacade.deleteSellPlatformStandardUnitWithTx(dto);
	}


}
	