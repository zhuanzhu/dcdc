package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.product.business.StandardUnitCombinationCategoryManage;
import com.egeo.components.product.facade.StandardUnitCombinationCategoryFacade;
import com.egeo.components.product.dto.StandardUnitCombinationCategoryDTO;
import com.egeo.components.product.dto.StandardUnitCombinationDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardUnitCombinationCategory")
public class StandardUnitCombinationCategoryManageImpl implements StandardUnitCombinationCategoryManage{

	
	@Resource(name="standardUnitCombinationCategoryFacade")
	private StandardUnitCombinationCategoryFacade standardUnitCombinationCategoryFacade;

	@Override
	public StandardUnitCombinationCategoryDTO findStandardUnitCombinationCategoryById(StandardUnitCombinationCategoryDTO dto) {
		return standardUnitCombinationCategoryFacade.findStandardUnitCombinationCategoryById(dto);
	}

	@Override
	public PageResult<StandardUnitCombinationCategoryDTO> findStandardUnitCombinationCategoryOfPage(StandardUnitCombinationCategoryDTO dto, Pagination page) {
		return standardUnitCombinationCategoryFacade.findStandardUnitCombinationCategoryOfPage(dto, page);
	}

	@Override
	public List<StandardUnitCombinationCategoryDTO> findStandardUnitCombinationCategoryAll(StandardUnitCombinationCategoryDTO dto) {
		return standardUnitCombinationCategoryFacade.findStandardUnitCombinationCategoryAll(dto);
	}

	@Override
	public Long insertStandardUnitCombinationCategoryWithTx(StandardUnitCombinationCategoryDTO dto) {
		return standardUnitCombinationCategoryFacade.insertStandardUnitCombinationCategoryWithTx(dto);
	}

	@Override
	public int updateStandardUnitCombinationCategoryWithTx(StandardUnitCombinationCategoryDTO dto) {
		return standardUnitCombinationCategoryFacade.updateStandardUnitCombinationCategoryWithTx(dto);
	}

	@Override
	public int deleteStandardUnitCombinationCategoryWithTx(StandardUnitCombinationCategoryDTO dto) {
		return standardUnitCombinationCategoryFacade.deleteStandardUnitCombinationCategoryWithTx(dto);
	}

	/**
	 * 批量保存su组合和前台类目节点关系
	 * @param sUCombinationDTO
	 * @param categoryTreeNodeIdList
	 * @return
	 */
	@Override
	public boolean saveStandardUnitCombinationCategoryAllWithTx(StandardUnitCombinationDTO sUCombinationDTO,
																List<Long> categoryTreeNodeIdList) {
		return standardUnitCombinationCategoryFacade.saveStandardUnitCombinationCategoryAllWithTx(
				sUCombinationDTO, categoryTreeNodeIdList);
	}


}
	