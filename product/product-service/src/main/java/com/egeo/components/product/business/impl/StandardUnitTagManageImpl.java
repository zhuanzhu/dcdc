package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.StandardUnitTagManage;
import com.egeo.components.product.facade.StandardUnitTagFacade;
import com.egeo.components.product.dto.StandardUnitTagDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardUnitTag")
public class StandardUnitTagManageImpl implements StandardUnitTagManage{

	
	@Resource(name="standardUnitTagFacade")
	private StandardUnitTagFacade standardUnitTagFacade;

	@Override
	public StandardUnitTagDTO findStandardUnitTagById(StandardUnitTagDTO dto) {
		return standardUnitTagFacade.findStandardUnitTagById(dto);
	}

	@Override
	public PageResult<StandardUnitTagDTO> findStandardUnitTagOfPage(StandardUnitTagDTO dto, Pagination page) {
		return standardUnitTagFacade.findStandardUnitTagOfPage(dto, page);
	}

	@Override
	public List<StandardUnitTagDTO> findStandardUnitTagAll(StandardUnitTagDTO dto) {
		return standardUnitTagFacade.findStandardUnitTagAll(dto);
	}

	@Override
	public Long insertStandardUnitTagWithTx(StandardUnitTagDTO dto) {
		return standardUnitTagFacade.insertStandardUnitTagWithTx(dto);
	}

	@Override
	public int updateStandardUnitTagWithTx(StandardUnitTagDTO dto) {
		return standardUnitTagFacade.updateStandardUnitTagWithTx(dto);
	}

	@Override
	public int deleteStandardUnitTagWithTx(StandardUnitTagDTO dto) {
		return standardUnitTagFacade.deleteStandardUnitTagWithTx(dto);
	}


}
	