package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.SkuAttValueReadService;
import com.egeo.components.product.manage.read.SkuAttValueReadManage;
import com.egeo.components.product.converter.SkuAttValueConverter;
import com.egeo.components.product.dto.SkuAttValueDTO;
import com.egeo.components.product.po.SkuAttValuePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("skuAttValueReadService")
public class SkuAttValueReadServiceImpl  implements SkuAttValueReadService {
	@Autowired
	private SkuAttValueReadManage skuAttValueReadManage;

	@Override
	public SkuAttValueDTO findSkuAttValueById(SkuAttValueDTO dto) {
		SkuAttValuePO po = SkuAttValueConverter.toPO(dto);
		SkuAttValuePO list = skuAttValueReadManage.findSkuAttValueById(po);		
		return SkuAttValueConverter.toDTO(list);
	}

	@Override
	public PageResult<SkuAttValueDTO> findSkuAttValueOfPage(SkuAttValueDTO dto, Pagination page) {
		SkuAttValuePO po = SkuAttValueConverter.toPO(dto);
        PageResult<SkuAttValuePO> pageResult = skuAttValueReadManage.findSkuAttValueOfPage(po, page);
        
        List<SkuAttValueDTO> list = SkuAttValueConverter.toDTO(pageResult.getList());
        PageResult<SkuAttValueDTO> result = new PageResult<SkuAttValueDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<SkuAttValueDTO> findSkuAttValueAll(SkuAttValueDTO dto) {
		SkuAttValuePO po = SkuAttValueConverter.toPO(dto);
		List<SkuAttValuePO> list = skuAttValueReadManage.findSkuAttValueAll(po);		
		return SkuAttValueConverter.toDTO(list);
	}
}
	