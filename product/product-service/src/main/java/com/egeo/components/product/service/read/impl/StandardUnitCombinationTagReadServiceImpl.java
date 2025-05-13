package com.egeo.components.product.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.StandardUnitCombinationTagReadService;
import com.egeo.components.product.manage.read.StandardUnitCombinationTagReadManage;
import com.egeo.components.product.condition.StandardUnitCombinationTagCondition;
import com.egeo.components.product.converter.StandardUnitCombinationTagConverter;
import com.egeo.components.product.dto.StandardUnitCombinationTagDTO;
import com.egeo.components.product.po.StandardUnitCombinationTagPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardUnitCombinationTagReadService")
public class StandardUnitCombinationTagReadServiceImpl  implements StandardUnitCombinationTagReadService {
	@Autowired
	private StandardUnitCombinationTagReadManage standardUnitCombinationTagReadManage;

	@Override
	public StandardUnitCombinationTagDTO findStandardUnitCombinationTagById(StandardUnitCombinationTagDTO dto) {
		StandardUnitCombinationTagPO po = StandardUnitCombinationTagConverter.toPO(dto);
		StandardUnitCombinationTagPO list = standardUnitCombinationTagReadManage.findStandardUnitCombinationTagById(po);		
		return StandardUnitCombinationTagConverter.toDTO(list);
	}

	@Override
	public PageResult<StandardUnitCombinationTagDTO> findStandardUnitCombinationTagOfPage(StandardUnitCombinationTagDTO dto, Pagination page) {
		StandardUnitCombinationTagPO po = StandardUnitCombinationTagConverter.toPO(dto);
        PageResult<StandardUnitCombinationTagPO> pageResult = standardUnitCombinationTagReadManage.findStandardUnitCombinationTagOfPage(po, page);
        
        List<StandardUnitCombinationTagDTO> list = StandardUnitCombinationTagConverter.toDTO(pageResult.getList());
        PageResult<StandardUnitCombinationTagDTO> result = new PageResult<StandardUnitCombinationTagDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<StandardUnitCombinationTagDTO> findStandardUnitCombinationTagAll(StandardUnitCombinationTagDTO dto) {
		StandardUnitCombinationTagPO po = StandardUnitCombinationTagConverter.toPO(dto);
		List<StandardUnitCombinationTagPO> list = standardUnitCombinationTagReadManage.findStandardUnitCombinationTagAll(po);		
		return StandardUnitCombinationTagConverter.toDTO(list);
	}
	/**
	 * 根据su组合id查询su组合标签信息
	 * @param standardUnitCombinationId
	 * @return
	 */
	@Override
	public List<StandardUnitCombinationTagDTO> findTagByStandardUnitCombinationId(Long standardUnitCombinationId) {
		List<StandardUnitCombinationTagDTO> list = new ArrayList<>();
		List<StandardUnitCombinationTagCondition> standardUnitCombinationTagConditionList = standardUnitCombinationTagReadManage.findTagByStandardUnitCombinationId(standardUnitCombinationId);
		for (StandardUnitCombinationTagCondition standardUnitCombinationTagCondition : standardUnitCombinationTagConditionList) {
			StandardUnitCombinationTagDTO standardUnitCombinationTagDTO = StandardUnitCombinationTagConverter.toDTO(standardUnitCombinationTagCondition);
			standardUnitCombinationTagDTO.setTagName(standardUnitCombinationTagCondition.getTagName());
			list.add(standardUnitCombinationTagDTO);
		}
		return list;
	}
}
	