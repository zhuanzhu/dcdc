package com.egeo.components.product.service.read.impl;

import java.util.List;

import com.egeo.utils.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.SellPlatformReadService;
import com.egeo.components.product.manage.read.SellPlatformReadManage;
import com.egeo.components.product.converter.SellPlatformConverter;
import com.egeo.components.product.dto.SellPlatformDTO;
import com.egeo.components.product.po.SellPlatformPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("sellPlatformReadService")
public class SellPlatformReadServiceImpl  implements SellPlatformReadService {
	@Autowired
	private SellPlatformReadManage sellPlatformReadManage;

	@Override
	public SellPlatformDTO findSellPlatformById(SellPlatformDTO dto) {
		SellPlatformPO po = SellPlatformConverter.toPO(dto);
		SellPlatformPO list = sellPlatformReadManage.findSellPlatformById(po);		
		return SellPlatformConverter.toDTO(list);
	}

	@Override
	public PageResult<SellPlatformDTO> findSellPlatformOfPage(SellPlatformDTO dto, Pagination page) {
		SellPlatformPO po = SellPlatformConverter.toPO(dto);
        PageResult<SellPlatformPO> pageResult = sellPlatformReadManage.findSellPlatformOfPage(po, page);
        
        List<SellPlatformDTO> list = SellPlatformConverter.toDTO(pageResult.getList());
        PageResult<SellPlatformDTO> result = new PageResult<SellPlatformDTO>();
        if(EmptyUtil.isNotEmpty(list)){
        	for(SellPlatformDTO platformDTO:list){
				if(platformDTO.getSortValue()==-1){
					platformDTO.setSortValue(null);
				}
			}

		}
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<SellPlatformDTO> findSellPlatformAll(SellPlatformDTO dto) {
		SellPlatformPO po = SellPlatformConverter.toPO(dto);
		List<SellPlatformPO> list = sellPlatformReadManage.findSellPlatformAll(po);		
		return SellPlatformConverter.toDTO(list);
	}
}
	