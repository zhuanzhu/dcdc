package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.SellPlatformStandardUnitReadService;
import com.egeo.components.product.service.write.SellPlatformStandardUnitWriteService;
import com.egeo.components.product.dto.SellPlatformStandardUnitDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class SellPlatformStandardUnitFacade {
	
	@Resource
	private SellPlatformStandardUnitReadService sellPlatformStandardUnitReadService;
	
	@Resource
	private SellPlatformStandardUnitWriteService sellPlatformStandardUnitWriteService;
	
	
	public SellPlatformStandardUnitDTO findSellPlatformStandardUnitById(SellPlatformStandardUnitDTO dto){
		
		return sellPlatformStandardUnitReadService.findSellPlatformStandardUnitById(dto);
	}

	public PageResult<SellPlatformStandardUnitDTO> findSellPlatformStandardUnitOfPage(SellPlatformStandardUnitDTO dto,Pagination page){
		
		return sellPlatformStandardUnitReadService.findSellPlatformStandardUnitOfPage(dto, page);
		
	}

	public List<SellPlatformStandardUnitDTO> findSellPlatformStandardUnitAll(SellPlatformStandardUnitDTO dto){
		
		return sellPlatformStandardUnitReadService.findSellPlatformStandardUnitAll(dto);
		
	}

	public Long insertSellPlatformStandardUnitWithTx(SellPlatformStandardUnitDTO dto){
		
		return sellPlatformStandardUnitWriteService.insertSellPlatformStandardUnitWithTx(dto);
	}

	public int updateSellPlatformStandardUnitWithTx(SellPlatformStandardUnitDTO dto){
		
		return sellPlatformStandardUnitWriteService.updateSellPlatformStandardUnitWithTx(dto);
	}

	public int deleteSellPlatformStandardUnitWithTx(SellPlatformStandardUnitDTO dto){
		
		return sellPlatformStandardUnitWriteService.deleteSellPlatformStandardUnitWithTx(dto);
		
	}
	/**
	 * 根据suid删除su比价平台信息
	 * @param merchantProdId
	 * @return
	 */
	public int deleteByStandardUnitIdWithTx(Long merchantProdId) {
		// TODO Auto-generated method stub
		return sellPlatformStandardUnitWriteService.deleteByStandardUnitIdWithTx(merchantProdId);
	}

}
	