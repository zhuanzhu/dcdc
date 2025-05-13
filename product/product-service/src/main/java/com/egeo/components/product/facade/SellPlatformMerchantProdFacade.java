package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.SellPlatformMerchantProdReadService;
import com.egeo.components.product.service.write.SellPlatformMerchantProdWriteService;
import com.egeo.components.product.dto.SellPlatformMerchantProdDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class SellPlatformMerchantProdFacade {
	
	@Resource
	private SellPlatformMerchantProdReadService sellPlatformMerchantProdReadService;
	
	@Resource
	private SellPlatformMerchantProdWriteService sellPlatformMerchantProdWriteService;
	
	
	public SellPlatformMerchantProdDTO findSellPlatformMerchantProdById(SellPlatformMerchantProdDTO dto){
		
		return sellPlatformMerchantProdReadService.findSellPlatformMerchantProdById(dto);
	}

	public PageResult<SellPlatformMerchantProdDTO> findSellPlatformMerchantProdOfPage(SellPlatformMerchantProdDTO dto,Pagination page){
		
		return sellPlatformMerchantProdReadService.findSellPlatformMerchantProdOfPage(dto, page);
		
	}

	public List<SellPlatformMerchantProdDTO> findSellPlatformMerchantProdAll(SellPlatformMerchantProdDTO dto){
		
		return sellPlatformMerchantProdReadService.findSellPlatformMerchantProdAll(dto);
		
	}

	public Long insertSellPlatformMerchantProdWithTx(SellPlatformMerchantProdDTO dto){
		
		return sellPlatformMerchantProdWriteService.insertSellPlatformMerchantProdWithTx(dto);
	}

	public int updateSellPlatformMerchantProdWithTx(SellPlatformMerchantProdDTO dto){
		
		return sellPlatformMerchantProdWriteService.updateSellPlatformMerchantProdWithTx(dto);
	}

	public int deleteSellPlatformMerchantProdWithTx(SellPlatformMerchantProdDTO dto){
		
		return sellPlatformMerchantProdWriteService.deleteSellPlatformMerchantProdWithTx(dto);
		
	}
	/**
	 * 根据su草稿id查询su草稿比价平台信息
	 * @param sellPlatformMerchantProdDTO
	 * @return
	 */
	public List<SellPlatformMerchantProdDTO> findByMerchantProdId(
			SellPlatformMerchantProdDTO sellPlatformMerchantProdDTO) {
		// TODO Auto-generated method stub
		return sellPlatformMerchantProdReadService.findByMerchantProdId(sellPlatformMerchantProdDTO);
	}

}
	