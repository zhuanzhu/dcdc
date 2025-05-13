package com.egeo.components.product.business.impl;
	

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.StandardProductUnitAttNameManage;
import com.egeo.components.product.facade.StandardProductUnitAttNameFacade;
import com.egeo.components.product.dto.StandardProductUnitAttNameDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardProductUnitAttName")
public class StandardProductUnitAttNameManageImpl implements StandardProductUnitAttNameManage{

	
	@Resource(name="standardProductUnitAttNameFacade")
	private StandardProductUnitAttNameFacade standardProductUnitAttNameFacade;

	@Override
	public StandardProductUnitAttNameDTO findStandardProductUnitAttNameById(StandardProductUnitAttNameDTO dto) {
		return standardProductUnitAttNameFacade.findStandardProductUnitAttNameById(dto);
	}

	@Override
	public PageResult<StandardProductUnitAttNameDTO> findStandardProductUnitAttNameOfPage(StandardProductUnitAttNameDTO dto, Pagination page) {
		return standardProductUnitAttNameFacade.findStandardProductUnitAttNameOfPage(dto, page);
	}

	@Override
	public List<StandardProductUnitAttNameDTO> findStandardProductUnitAttNameAll(StandardProductUnitAttNameDTO dto) {
		return standardProductUnitAttNameFacade.findStandardProductUnitAttNameAll(dto);
	}

	@Override
	public Long insertStandardProductUnitAttNameWithTx(StandardProductUnitAttNameDTO dto) {
		return standardProductUnitAttNameFacade.insertStandardProductUnitAttNameWithTx(dto);
	}

	@Override
	public int updateStandardProductUnitAttNameWithTx(StandardProductUnitAttNameDTO dto) {
		return standardProductUnitAttNameFacade.updateStandardProductUnitAttNameWithTx(dto);
	}

	@Override
	public int deleteStandardProductUnitAttNameWithTx(StandardProductUnitAttNameDTO dto) {
		return standardProductUnitAttNameFacade.deleteStandardProductUnitAttNameWithTx(dto);
	}
	/**
	 * 根据suid查询spu规格属性
	 * @param standardUnitId
	 * @param req
	 * @return
	 */
	@Override
	public List<Map<String, Object>> findByStandardUnitId(Long standardUnitId) {
		// TODO Auto-generated method stub
		return standardProductUnitAttNameFacade.findByStandardUnitId(standardUnitId);
	}


}
	