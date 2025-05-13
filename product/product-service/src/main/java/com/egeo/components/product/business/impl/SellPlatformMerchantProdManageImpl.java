package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.SellPlatformMerchantProdManage;
import com.egeo.components.product.facade.SellPlatformMerchantProdFacade;
import com.egeo.components.product.dto.SellPlatformMerchantProdDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("sellPlatformMerchantProd")
public class SellPlatformMerchantProdManageImpl implements SellPlatformMerchantProdManage{

	
	@Resource(name="sellPlatformMerchantProdFacade")
	private SellPlatformMerchantProdFacade sellPlatformMerchantProdFacade;

	@Override
	public SellPlatformMerchantProdDTO findSellPlatformMerchantProdById(SellPlatformMerchantProdDTO dto) {
		return sellPlatformMerchantProdFacade.findSellPlatformMerchantProdById(dto);
	}

	@Override
	public PageResult<SellPlatformMerchantProdDTO> findSellPlatformMerchantProdOfPage(SellPlatformMerchantProdDTO dto, Pagination page) {
		return sellPlatformMerchantProdFacade.findSellPlatformMerchantProdOfPage(dto, page);
	}

	@Override
	public List<SellPlatformMerchantProdDTO> findSellPlatformMerchantProdAll(SellPlatformMerchantProdDTO dto) {
		return sellPlatformMerchantProdFacade.findSellPlatformMerchantProdAll(dto);
	}

	@Override
	public Long insertSellPlatformMerchantProdWithTx(SellPlatformMerchantProdDTO dto) {
		return sellPlatformMerchantProdFacade.insertSellPlatformMerchantProdWithTx(dto);
	}

	@Override
	public int updateSellPlatformMerchantProdWithTx(SellPlatformMerchantProdDTO dto) {
		return sellPlatformMerchantProdFacade.updateSellPlatformMerchantProdWithTx(dto);
	}

	@Override
	public int deleteSellPlatformMerchantProdWithTx(SellPlatformMerchantProdDTO dto) {
		return sellPlatformMerchantProdFacade.deleteSellPlatformMerchantProdWithTx(dto);
	}


}
	