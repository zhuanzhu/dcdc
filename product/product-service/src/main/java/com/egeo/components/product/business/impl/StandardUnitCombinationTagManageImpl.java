package com.egeo.components.product.business.impl;
	

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.product.business.StandardUnitCombinationTagManage;
import com.egeo.components.product.facade.StandardUnitCombinationTagFacade;
import com.egeo.components.product.dto.StandardUnitCombinationDTO;
import com.egeo.components.product.dto.StandardUnitCombinationTagDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardUnitCombinationTag")
public class StandardUnitCombinationTagManageImpl implements StandardUnitCombinationTagManage{

	
	@Resource(name="standardUnitCombinationTagFacade")
	private StandardUnitCombinationTagFacade standardUnitCombinationTagFacade;

	@Override
	public StandardUnitCombinationTagDTO findStandardUnitCombinationTagById(StandardUnitCombinationTagDTO dto) {
		return standardUnitCombinationTagFacade.findStandardUnitCombinationTagById(dto);
	}

	@Override
	public PageResult<StandardUnitCombinationTagDTO> findStandardUnitCombinationTagOfPage(StandardUnitCombinationTagDTO dto, Pagination page) {
		return standardUnitCombinationTagFacade.findStandardUnitCombinationTagOfPage(dto, page);
	}

	@Override
	public List<StandardUnitCombinationTagDTO> findStandardUnitCombinationTagAll(StandardUnitCombinationTagDTO dto) {
		return standardUnitCombinationTagFacade.findStandardUnitCombinationTagAll(dto);
	}

	@Override
	public Long insertStandardUnitCombinationTagWithTx(StandardUnitCombinationTagDTO dto) {
		return standardUnitCombinationTagFacade.insertStandardUnitCombinationTagWithTx(dto);
	}

	@Override
	public int updateStandardUnitCombinationTagWithTx(StandardUnitCombinationTagDTO dto) {
		return standardUnitCombinationTagFacade.updateStandardUnitCombinationTagWithTx(dto);
	}

	@Override
	public int deleteStandardUnitCombinationTagWithTx(StandardUnitCombinationTagDTO dto) {
		return standardUnitCombinationTagFacade.deleteStandardUnitCombinationTagWithTx(dto);
	}
	/**
	 * 根据su组合id查询su组合标签信息
	 * @param standardUnitCombinationId
	 * @return
	 */
	@Override
	public List<Map<String, Object>> findTagByStandardUnitCombinationId(Long standardUnitCombinationId) {
		// TODO Auto-generated method stub
		return standardUnitCombinationTagFacade.findTagByStandardUnitCombinationId(standardUnitCombinationId);
	}
	/**
	 * 批量保存su组合id与标签关系信息
	 * @param standardUnitCombinationId
	 * @return
	 */
	@Override
	public Integer saveStandardUnitCombinationTagWithTx(StandardUnitCombinationDTO standardUnitCombinationDTO, List<Long> tagIdList) {
		// TODO Auto-generated method stub
		return standardUnitCombinationTagFacade.saveStandardUnitCombinationTagWithTx(standardUnitCombinationDTO,tagIdList);
	}


}
	