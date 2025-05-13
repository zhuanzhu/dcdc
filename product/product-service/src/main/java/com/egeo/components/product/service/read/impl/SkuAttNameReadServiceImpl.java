package com.egeo.components.product.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.SkuAttNameReadService;
import com.egeo.components.product.manage.read.SkuAttNameReadManage;
import com.egeo.components.product.condition.SkuAttNameCondition;
import com.egeo.components.product.converter.SkuAttNameConverter;
import com.egeo.components.product.dto.SkuAttNameDTO;
import com.egeo.components.product.po.SkuAttNamePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("skuAttNameReadService")
public class SkuAttNameReadServiceImpl  implements SkuAttNameReadService {
	@Autowired
	private SkuAttNameReadManage skuAttNameReadManage;

	@Override
	public SkuAttNameDTO findSkuAttNameById(SkuAttNameDTO dto) {
		SkuAttNamePO po = SkuAttNameConverter.toPO(dto);
		SkuAttNamePO list = skuAttNameReadManage.findSkuAttNameById(po);		
		return SkuAttNameConverter.toDTO(list);
	}

	@Override
	public PageResult<SkuAttNameDTO> findSkuAttNameOfPage(SkuAttNameDTO dto, Pagination page) {
		SkuAttNamePO po = SkuAttNameConverter.toPO(dto);
        PageResult<SkuAttNamePO> pageResult = skuAttNameReadManage.findSkuAttNameOfPage(po, page);
        
        List<SkuAttNameDTO> list = SkuAttNameConverter.toDTO(pageResult.getList());
        PageResult<SkuAttNameDTO> result = new PageResult<SkuAttNameDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<SkuAttNameDTO> findSkuAttNameAll(SkuAttNameDTO dto) {
		SkuAttNamePO po = SkuAttNameConverter.toPO(dto);
		List<SkuAttNamePO> list = skuAttNameReadManage.findSkuAttNameAll(po);		
		return SkuAttNameConverter.toDTO(list);
	}
	/**
	 * 根据skuid查询sku属性和属性值集合
	 * @param skuId
	 * @return
	 */
	@Override
	public List<SkuAttNameDTO> findSkuAttNameByskuId(Long skuId) {
		List<SkuAttNameDTO> skuAttNameList = new ArrayList<>();
		List<SkuAttNameCondition> list = skuAttNameReadManage.findSkuAttNameByskuId(skuId);
		for (SkuAttNameCondition skuAttNameCondition : list) {
			SkuAttNameDTO skuAttNameDTO = SkuAttNameConverter.toDTO(skuAttNameCondition);
			skuAttNameDTO.setName(skuAttNameCondition.getName());
			skuAttNameDTO.setValue(skuAttNameCondition.getValue());
			skuAttNameList.add(skuAttNameDTO);
		}
		return skuAttNameList;
	}

	@Override
	public Long findLastId() {
		return skuAttNameReadManage.findLastId();
	}
}
	