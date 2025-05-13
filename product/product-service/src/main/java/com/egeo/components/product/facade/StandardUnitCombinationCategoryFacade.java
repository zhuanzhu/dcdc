package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.StandardUnitCombinationCategoryReadService;
import com.egeo.components.product.service.write.StandardUnitCombinationCategoryWriteService;
import com.egeo.components.product.dto.StandardUnitCombinationCategoryDTO;
import com.egeo.components.product.dto.StandardUnitCombinationDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class StandardUnitCombinationCategoryFacade {
	
	@Resource
	private StandardUnitCombinationCategoryReadService standardUnitCombinationCategoryReadService;
	
	@Resource
	private StandardUnitCombinationCategoryWriteService standardUnitCombinationCategoryWriteService;
	
	
	public StandardUnitCombinationCategoryDTO findStandardUnitCombinationCategoryById(StandardUnitCombinationCategoryDTO dto){
		
		return standardUnitCombinationCategoryReadService.findStandardUnitCombinationCategoryById(dto);
	}

	public PageResult<StandardUnitCombinationCategoryDTO> findStandardUnitCombinationCategoryOfPage(StandardUnitCombinationCategoryDTO dto,Pagination page){
		
		return standardUnitCombinationCategoryReadService.findStandardUnitCombinationCategoryOfPage(dto, page);
		
	}

	public List<StandardUnitCombinationCategoryDTO> findStandardUnitCombinationCategoryAll(StandardUnitCombinationCategoryDTO dto){
		
		return standardUnitCombinationCategoryReadService.findStandardUnitCombinationCategoryAll(dto);
		
	}

	public Long insertStandardUnitCombinationCategoryWithTx(StandardUnitCombinationCategoryDTO dto){
		
		return standardUnitCombinationCategoryWriteService.insertStandardUnitCombinationCategoryWithTx(dto);
	}

	public int updateStandardUnitCombinationCategoryWithTx(StandardUnitCombinationCategoryDTO dto){
		
		return standardUnitCombinationCategoryWriteService.updateStandardUnitCombinationCategoryWithTx(dto);
	}

	public int deleteStandardUnitCombinationCategoryWithTx(StandardUnitCombinationCategoryDTO dto){
		
		return standardUnitCombinationCategoryWriteService.deleteStandardUnitCombinationCategoryWithTx(dto);
		
	}

	/**
	 * 批量保存su组合和前台类目节点关系
	 * @param sUCombinationDTO
	 * @param categoryTreeNodeIdList
	 * @return
	 */
	public boolean saveStandardUnitCombinationCategoryAllWithTx(StandardUnitCombinationDTO sUCombinationDTO,
																List<Long> categoryTreeNodeIdList) {
		return standardUnitCombinationCategoryWriteService.saveStandardUnitCombinationCategoryAllWithTx(
				sUCombinationDTO, categoryTreeNodeIdList);
	}

}
	