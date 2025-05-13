package com.egeo.components.order.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.SoDeliveryItemManage;
import com.egeo.components.order.facade.SoDeliveryItemFacade;
import com.egeo.components.order.dto.SoDeliveryItemDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("soDeliveryItem")
public class SoDeliveryItemManageImpl implements SoDeliveryItemManage{

	
	@Resource(name="soDeliveryItemFacade")
	private SoDeliveryItemFacade soDeliveryItemFacade;

	@Override
	public SoDeliveryItemDTO findSoDeliveryItemById(SoDeliveryItemDTO dto) {
		return soDeliveryItemFacade.findSoDeliveryItemById(dto);
	}

	@Override
	public PageResult<SoDeliveryItemDTO> findSoDeliveryItemOfPage(SoDeliveryItemDTO dto, Pagination page) {
		return soDeliveryItemFacade.findSoDeliveryItemOfPage(dto, page);
	}

	@Override
	public List<SoDeliveryItemDTO> findSoDeliveryItemAll(SoDeliveryItemDTO dto) {
		return soDeliveryItemFacade.findSoDeliveryItemAll(dto);
	}

	@Override
	public int insertSoDeliveryItemWithTx(SoDeliveryItemDTO dto) {
		return soDeliveryItemFacade.insertSoDeliveryItemWithTx(dto);
	}

	@Override
	public int updateSoDeliveryItemWithTx(SoDeliveryItemDTO dto) {
		return soDeliveryItemFacade.updateSoDeliveryItemWithTx(dto);
	}

	@Override
	public int deleteSoDeliveryItemWithTx(SoDeliveryItemDTO dto) {
		return soDeliveryItemFacade.deleteSoDeliveryItemWithTx(dto);
	}


}
	