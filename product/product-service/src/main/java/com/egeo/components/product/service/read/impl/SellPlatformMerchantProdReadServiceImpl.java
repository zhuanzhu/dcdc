package com.egeo.components.product.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.SellPlatformMerchantProdReadService;
import com.egeo.components.product.manage.read.SellPlatformMerchantProdReadManage;
import com.egeo.components.product.condition.SellPlatformMerchantProdCondition;
import com.egeo.components.product.converter.SellPlatformMerchantProdConverter;
import com.egeo.components.product.dto.SellPlatformMerchantProdDTO;
import com.egeo.components.product.po.SellPlatformMerchantProdPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("sellPlatformMerchantProdReadService")
public class SellPlatformMerchantProdReadServiceImpl  implements SellPlatformMerchantProdReadService {
	@Autowired
	private SellPlatformMerchantProdReadManage sellPlatformMerchantProdReadManage;

	@Override
	public SellPlatformMerchantProdDTO findSellPlatformMerchantProdById(SellPlatformMerchantProdDTO dto) {
		SellPlatformMerchantProdPO po = SellPlatformMerchantProdConverter.toPO(dto);
		SellPlatformMerchantProdPO list = sellPlatformMerchantProdReadManage.findSellPlatformMerchantProdById(po);		
		return SellPlatformMerchantProdConverter.toDTO(list);
	}

	@Override
	public PageResult<SellPlatformMerchantProdDTO> findSellPlatformMerchantProdOfPage(SellPlatformMerchantProdDTO dto, Pagination page) {
		SellPlatformMerchantProdPO po = SellPlatformMerchantProdConverter.toPO(dto);
        PageResult<SellPlatformMerchantProdPO> pageResult = sellPlatformMerchantProdReadManage.findSellPlatformMerchantProdOfPage(po, page);
        
        List<SellPlatformMerchantProdDTO> list = SellPlatformMerchantProdConverter.toDTO(pageResult.getList());
        PageResult<SellPlatformMerchantProdDTO> result = new PageResult<SellPlatformMerchantProdDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<SellPlatformMerchantProdDTO> findSellPlatformMerchantProdAll(SellPlatformMerchantProdDTO dto) {
		SellPlatformMerchantProdPO po = SellPlatformMerchantProdConverter.toPO(dto);
		List<SellPlatformMerchantProdPO> list = sellPlatformMerchantProdReadManage.findSellPlatformMerchantProdAll(po);		
		return SellPlatformMerchantProdConverter.toDTO(list);
	}
	/**
	 * 根据su草稿id查询su草稿比价平台信息
	 * @param sellPlatformMerchantProdDTO
	 * @return
	 */
	@Override
	public List<SellPlatformMerchantProdDTO> findByMerchantProdId(
			SellPlatformMerchantProdDTO sellPlatformMerchantProdDTO) {
		List<SellPlatformMerchantProdDTO> sellPlatformMerchantProdDTOs = new ArrayList<>();
		List<SellPlatformMerchantProdCondition> list = sellPlatformMerchantProdReadManage.findByMerchantProdId(SellPlatformMerchantProdConverter.toPO(sellPlatformMerchantProdDTO));
		for (SellPlatformMerchantProdCondition sellPlatformMerchantProdCondition : list) {
			SellPlatformMerchantProdDTO sellPlatformMerchantProdDTO2 = SellPlatformMerchantProdConverter.toDTO(sellPlatformMerchantProdCondition);
			sellPlatformMerchantProdDTO2.setName(sellPlatformMerchantProdCondition.getName());
			sellPlatformMerchantProdDTOs.add(sellPlatformMerchantProdDTO2);
		}
		return sellPlatformMerchantProdDTOs;
	}
}
	