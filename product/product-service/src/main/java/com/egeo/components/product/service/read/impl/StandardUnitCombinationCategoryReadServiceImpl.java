package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.StandardUnitCombinationCategoryReadService;
import com.egeo.components.product.manage.read.StandardUnitCombinationCategoryReadManage;
import com.egeo.components.product.converter.StandardUnitCombinationCategoryConverter;
import com.egeo.components.product.converter.StandardUnitConverter;
import com.egeo.components.product.dto.StandardUnitCombinationCategoryDTO;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.product.po.StandardUnitCombinationCategoryPO;
import com.egeo.components.product.po.StandardUnitPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardUnitCombinationCategoryReadService")
public class StandardUnitCombinationCategoryReadServiceImpl  implements StandardUnitCombinationCategoryReadService {
	@Autowired
	private StandardUnitCombinationCategoryReadManage standardUnitCombinationCategoryReadManage;

	@Override
	public StandardUnitCombinationCategoryDTO findStandardUnitCombinationCategoryById(StandardUnitCombinationCategoryDTO dto) {
		StandardUnitCombinationCategoryPO po = StandardUnitCombinationCategoryConverter.toPO(dto);
		StandardUnitCombinationCategoryPO list = standardUnitCombinationCategoryReadManage.findStandardUnitCombinationCategoryById(po);		
		return StandardUnitCombinationCategoryConverter.toDTO(list);
	}

	@Override
	public PageResult<StandardUnitCombinationCategoryDTO> findStandardUnitCombinationCategoryOfPage(StandardUnitCombinationCategoryDTO dto, Pagination page) {
		StandardUnitCombinationCategoryPO po = StandardUnitCombinationCategoryConverter.toPO(dto);
        PageResult<StandardUnitCombinationCategoryPO> pageResult = standardUnitCombinationCategoryReadManage.findStandardUnitCombinationCategoryOfPage(po, page);
        
        List<StandardUnitCombinationCategoryDTO> list = StandardUnitCombinationCategoryConverter.toDTO(pageResult.getList());
        PageResult<StandardUnitCombinationCategoryDTO> result = new PageResult<StandardUnitCombinationCategoryDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<StandardUnitCombinationCategoryDTO> findStandardUnitCombinationCategoryAll(StandardUnitCombinationCategoryDTO dto) {
		StandardUnitCombinationCategoryPO po = StandardUnitCombinationCategoryConverter.toPO(dto);
		List<StandardUnitCombinationCategoryPO> list = standardUnitCombinationCategoryReadManage.findStandardUnitCombinationCategoryAll(po);		
		return StandardUnitCombinationCategoryConverter.toDTO(list);
	}
	/**
	 * 根据类目类型su组合id查询su商品数量
	 * @param id
	 * @return
	 */
	@Override
	public Integer findStandardUnitSizeByCategoryType(Long standardUnitCombinationId) {
		// TODO Auto-generated method stub
		return standardUnitCombinationCategoryReadManage.findStandardUnitSizeByCategoryType(standardUnitCombinationId);
	}
	/**
	 * 根据类目类型su组合id查询su商品列表
	 * @param standardUnitCombinationId
	 * @return
	 */
	@Override
	public PageResult<StandardUnitDTO> findByStandardUnitCombinationIdOfPage(
			Long standardUnitCombinationId, Pagination page) {
        PageResult<StandardUnitPO> pageResult = standardUnitCombinationCategoryReadManage.findByStandardUnitCombinationIdOfPage(standardUnitCombinationId, page);
        
        List<StandardUnitDTO> list = StandardUnitConverter.toDTO(pageResult.getList());
        PageResult<StandardUnitDTO> result = new PageResult<StandardUnitDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}
}
	