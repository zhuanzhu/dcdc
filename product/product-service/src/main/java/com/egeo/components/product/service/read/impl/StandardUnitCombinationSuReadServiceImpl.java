package com.egeo.components.product.service.read.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.StandardUnitCombinationSuReadService;
import com.egeo.components.product.manage.read.StandardUnitCombinationSuReadManage;
import com.egeo.components.product.condition.StandardUnitCombinationSuCondition;
import com.egeo.components.product.converter.StandardUnitCombinationSuConverter;
import com.egeo.components.product.dto.StandardUnitCombinationSuDTO;
import com.egeo.components.product.po.StandardUnitCombinationSuPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardUnitCombinationSuReadService")
public class StandardUnitCombinationSuReadServiceImpl  implements StandardUnitCombinationSuReadService {
	@Autowired
	private StandardUnitCombinationSuReadManage standardUnitCombinationSuReadManage;

	@Override
	public StandardUnitCombinationSuDTO findStandardUnitCombinationSuById(StandardUnitCombinationSuDTO dto) {
		StandardUnitCombinationSuPO po = StandardUnitCombinationSuConverter.toPO(dto);
		StandardUnitCombinationSuPO list = standardUnitCombinationSuReadManage.findStandardUnitCombinationSuById(po);		
		return StandardUnitCombinationSuConverter.toDTO(list);
	}

	@Override
	public PageResult<StandardUnitCombinationSuDTO> findStandardUnitCombinationSuOfPage(StandardUnitCombinationSuDTO dto, Pagination page) {
		StandardUnitCombinationSuPO po = StandardUnitCombinationSuConverter.toPO(dto);
        PageResult<StandardUnitCombinationSuPO> pageResult = standardUnitCombinationSuReadManage.findStandardUnitCombinationSuOfPage(po, page);
        
        List<StandardUnitCombinationSuDTO> list = StandardUnitCombinationSuConverter.toDTO(pageResult.getList());
        PageResult<StandardUnitCombinationSuDTO> result = new PageResult<StandardUnitCombinationSuDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<StandardUnitCombinationSuDTO> findStandardUnitCombinationSuAll(StandardUnitCombinationSuDTO dto) {
		StandardUnitCombinationSuPO po = StandardUnitCombinationSuConverter.toPO(dto);
		List<StandardUnitCombinationSuPO> list = standardUnitCombinationSuReadManage.findStandardUnitCombinationSuAll(po);		
		return StandardUnitCombinationSuConverter.toDTO(list);
	}
	/**
	 * 根据su组合id查询su商品列表
	 * @param standardUnitCombinationId
	 * @return
	 */
	@Override
	public PageResult<StandardUnitCombinationSuDTO> findByStandardUnitCombinationIdOfPage(Long standardUnitCombinationId,Pagination page) {
		List<StandardUnitCombinationSuDTO> list = new ArrayList<>();
		PageResult<StandardUnitCombinationSuCondition> pageResult = standardUnitCombinationSuReadManage.findByStandardUnitCombinationIdOfPage(standardUnitCombinationId, page);
        List<StandardUnitCombinationSuCondition> standardUnitCombinationSuConditionList = pageResult.getList();
        for (StandardUnitCombinationSuCondition standardUnitCombinationSuCondition : standardUnitCombinationSuConditionList) {
        	StandardUnitCombinationSuDTO standardUnitCombinationSuDTO = StandardUnitCombinationSuConverter.toDTO(standardUnitCombinationSuCondition);
        	standardUnitCombinationSuDTO.setStandardUnitName(standardUnitCombinationSuCondition.getStandardUnitName());
        	standardUnitCombinationSuDTO.setStandardUnitSalePrice(standardUnitCombinationSuCondition.getSalePrice());
			standardUnitCombinationSuDTO.setMerchantProductSerialNumber(standardUnitCombinationSuCondition.getMerchantProductSerialNumber());
			standardUnitCombinationSuDTO.setSupplierId(standardUnitCombinationSuCondition.getSupplierId());
			boolean isSell = Objects.equals(0,standardUnitCombinationSuCondition.getIsVendible()) &&Objects.equals(0,standardUnitCombinationSuCondition.getIsVisible());
			standardUnitCombinationSuDTO.setSellState(isSell?1:0);
			standardUnitCombinationSuDTO.setSource(standardUnitCombinationSuCondition.getSource());
        	list.add(standardUnitCombinationSuDTO);
		}
        PageResult<StandardUnitCombinationSuDTO> result = new PageResult<StandardUnitCombinationSuDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}
	/**
	 * 根据su组合id查询su商品数量
	 * @param id
	 * @return
	 */
	@Override
	public Integer findStandardUnitSize(Long standardUnitCombinationId) {
		// TODO Auto-generated method stub
		return standardUnitCombinationSuReadManage.findStandardUnitSize(standardUnitCombinationId);
	}

	@Override
	public List<StandardUnitCombinationSuDTO> syncJdSellState(Integer source, Date endCheckTime, int size) {
		return StandardUnitCombinationSuConverter.toDTO(standardUnitCombinationSuReadManage.syncJdSellState(source,endCheckTime,size));
	}
}
	