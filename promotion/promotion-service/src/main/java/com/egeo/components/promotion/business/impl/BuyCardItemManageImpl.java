package com.egeo.components.promotion.business.impl;


import com.egeo.components.promotion.business.BuyCardItemManage;
import com.egeo.components.promotion.dto.BuyCardItemDTO;
import com.egeo.components.promotion.facade.BuyCardItemFacade;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("buyCardItem")
public class BuyCardItemManageImpl implements BuyCardItemManage{


	@Resource(name="buyCardItemFacade")
	private BuyCardItemFacade buyCardItemFacade;

	@Override
	public BuyCardItemDTO findBuyCardItemById(BuyCardItemDTO dto) {
		return buyCardItemFacade.findBuyCardItemById(dto);
	}

	@Override
	public PageResult<BuyCardItemDTO> findBuyCardItemOfPage(BuyCardItemDTO dto, Pagination page) {
		return buyCardItemFacade.findBuyCardItemOfPage(dto, page);
	}

	@Override
	public List<BuyCardItemDTO> findBuyCardItemAll(BuyCardItemDTO dto) {
		return buyCardItemFacade.findBuyCardItemAll(dto);
	}

	@Override
	public Long insertBuyCardItemWithTx(BuyCardItemDTO dto) {
		return buyCardItemFacade.insertBuyCardItemWithTx(dto);
	}

	@Override
	public int updateBuyCardItemWithTx(BuyCardItemDTO dto) {
		return buyCardItemFacade.updateBuyCardItemWithTx(dto);
	}

	@Override
	public int deleteBuyCardItemWithTx(BuyCardItemDTO dto) {
		return buyCardItemFacade.deleteBuyCardItemWithTx(dto);
	}
}
