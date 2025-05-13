package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.SellPlatformStandardUnitReadService;
import com.egeo.components.product.manage.read.SellPlatformStandardUnitReadManage;
import com.egeo.components.product.converter.SellPlatformStandardUnitConverter;
import com.egeo.components.product.dto.SellPlatformStandardUnitDTO;
import com.egeo.components.product.po.SellPlatformStandardUnitPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("sellPlatformStandardUnitReadService")
public class SellPlatformStandardUnitReadServiceImpl  implements SellPlatformStandardUnitReadService {
	@Autowired
	private SellPlatformStandardUnitReadManage sellPlatformStandardUnitReadManage;

	@Override
	public SellPlatformStandardUnitDTO findSellPlatformStandardUnitById(SellPlatformStandardUnitDTO dto) {
		SellPlatformStandardUnitPO po = SellPlatformStandardUnitConverter.toPO(dto);
		SellPlatformStandardUnitPO list = sellPlatformStandardUnitReadManage.findSellPlatformStandardUnitById(po);		
		return SellPlatformStandardUnitConverter.toDTO(list);
	}

	@Override
	public PageResult<SellPlatformStandardUnitDTO> findSellPlatformStandardUnitOfPage(SellPlatformStandardUnitDTO dto, Pagination page) {
		SellPlatformStandardUnitPO po = SellPlatformStandardUnitConverter.toPO(dto);
        PageResult<SellPlatformStandardUnitPO> pageResult = sellPlatformStandardUnitReadManage.findSellPlatformStandardUnitOfPage(po, page);
        
        List<SellPlatformStandardUnitDTO> list = SellPlatformStandardUnitConverter.toDTO(pageResult.getList());
        PageResult<SellPlatformStandardUnitDTO> result = new PageResult<SellPlatformStandardUnitDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<SellPlatformStandardUnitDTO> findSellPlatformStandardUnitAll(SellPlatformStandardUnitDTO dto) {
		SellPlatformStandardUnitPO po = SellPlatformStandardUnitConverter.toPO(dto);
		List<SellPlatformStandardUnitPO> list = sellPlatformStandardUnitReadManage.findSellPlatformStandardUnitAll(po);		
		return SellPlatformStandardUnitConverter.toDTO(list);
	}
}
	