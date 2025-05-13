package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.SellPlatformStandardUnitRecordReadService;
import com.egeo.components.product.manage.read.SellPlatformStandardUnitRecordReadManage;
import com.egeo.components.product.converter.SellPlatformStandardUnitRecordConverter;
import com.egeo.components.product.dto.SellPlatformStandardUnitRecordDTO;
import com.egeo.components.product.po.SellPlatformStandardUnitRecordPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("sellPlatformStandardUnitRecordReadService")
public class SellPlatformStandardUnitRecordReadServiceImpl  implements SellPlatformStandardUnitRecordReadService {
	@Autowired
	private SellPlatformStandardUnitRecordReadManage sellPlatformStandardUnitRecordReadManage;

	@Override
	public SellPlatformStandardUnitRecordDTO findSellPlatformStandardUnitRecordById(SellPlatformStandardUnitRecordDTO dto) {
		SellPlatformStandardUnitRecordPO po = SellPlatformStandardUnitRecordConverter.toPO(dto);
		SellPlatformStandardUnitRecordPO list = sellPlatformStandardUnitRecordReadManage.findSellPlatformStandardUnitRecordById(po);		
		return SellPlatformStandardUnitRecordConverter.toDTO(list);
	}

	@Override
	public PageResult<SellPlatformStandardUnitRecordDTO> findSellPlatformStandardUnitRecordOfPage(SellPlatformStandardUnitRecordDTO dto, Pagination page) {
		SellPlatformStandardUnitRecordPO po = SellPlatformStandardUnitRecordConverter.toPO(dto);
        PageResult<SellPlatformStandardUnitRecordPO> pageResult = sellPlatformStandardUnitRecordReadManage.findSellPlatformStandardUnitRecordOfPage(po, page);
        
        List<SellPlatformStandardUnitRecordDTO> list = SellPlatformStandardUnitRecordConverter.toDTO(pageResult.getList());
        PageResult<SellPlatformStandardUnitRecordDTO> result = new PageResult<SellPlatformStandardUnitRecordDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<SellPlatformStandardUnitRecordDTO> findSellPlatformStandardUnitRecordAll(SellPlatformStandardUnitRecordDTO dto) {
		SellPlatformStandardUnitRecordPO po = SellPlatformStandardUnitRecordConverter.toPO(dto);
		List<SellPlatformStandardUnitRecordPO> list = sellPlatformStandardUnitRecordReadManage.findSellPlatformStandardUnitRecordAll(po);		
		return SellPlatformStandardUnitRecordConverter.toDTO(list);
	}
}
	