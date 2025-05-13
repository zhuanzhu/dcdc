package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.StandardProductUnitAttValueManage;
import com.egeo.components.product.facade.StandardProductUnitAttValueFacade;
import com.egeo.components.product.dto.StandardProductUnitAttValueDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardProductUnitAttValue")
public class StandardProductUnitAttValueManageImpl implements StandardProductUnitAttValueManage{

	
	@Resource(name="standardProductUnitAttValueFacade")
	private StandardProductUnitAttValueFacade standardProductUnitAttValueFacade;

	@Override
	public StandardProductUnitAttValueDTO findStandardProductUnitAttValueById(StandardProductUnitAttValueDTO dto) {
		return standardProductUnitAttValueFacade.findStandardProductUnitAttValueById(dto);
	}

	@Override
	public PageResult<StandardProductUnitAttValueDTO> findStandardProductUnitAttValueOfPage(StandardProductUnitAttValueDTO dto, Pagination page) {
		return standardProductUnitAttValueFacade.findStandardProductUnitAttValueOfPage(dto, page);
	}

	@Override
	public List<StandardProductUnitAttValueDTO> findStandardProductUnitAttValueAll(StandardProductUnitAttValueDTO dto) {
		return standardProductUnitAttValueFacade.findStandardProductUnitAttValueAll(dto);
	}

	@Override
	public Long insertStandardProductUnitAttValueWithTx(StandardProductUnitAttValueDTO dto) {
		return standardProductUnitAttValueFacade.insertStandardProductUnitAttValueWithTx(dto);
	}

	@Override
	public int updateStandardProductUnitAttValueWithTx(StandardProductUnitAttValueDTO dto) {
		return standardProductUnitAttValueFacade.updateStandardProductUnitAttValueWithTx(dto);
	}

	@Override
	public int deleteStandardProductUnitAttValueWithTx(StandardProductUnitAttValueDTO dto) {
		return standardProductUnitAttValueFacade.deleteStandardProductUnitAttValueWithTx(dto);
	}


}
	