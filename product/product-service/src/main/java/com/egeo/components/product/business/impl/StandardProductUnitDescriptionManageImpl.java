package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.StandardProductUnitDescriptionManage;
import com.egeo.components.product.facade.StandardProductUnitDescriptionFacade;
import com.egeo.components.product.dto.StandardProductUnitDescriptionDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardProductUnitDescription")
public class StandardProductUnitDescriptionManageImpl implements StandardProductUnitDescriptionManage{

	
	@Resource(name="standardProductUnitDescriptionFacade")
	private StandardProductUnitDescriptionFacade standardProductUnitDescriptionFacade;

	@Override
	public StandardProductUnitDescriptionDTO findStandardProductUnitDescriptionById(StandardProductUnitDescriptionDTO dto) {
		return standardProductUnitDescriptionFacade.findStandardProductUnitDescriptionById(dto);
	}

	@Override
	public PageResult<StandardProductUnitDescriptionDTO> findStandardProductUnitDescriptionOfPage(StandardProductUnitDescriptionDTO dto, Pagination page) {
		return standardProductUnitDescriptionFacade.findStandardProductUnitDescriptionOfPage(dto, page);
	}

	@Override
	public List<StandardProductUnitDescriptionDTO> findStandardProductUnitDescriptionAll(StandardProductUnitDescriptionDTO dto) {
		return standardProductUnitDescriptionFacade.findStandardProductUnitDescriptionAll(dto);
	}

	@Override
	public Long insertStandardProductUnitDescriptionWithTx(StandardProductUnitDescriptionDTO dto) {
		return standardProductUnitDescriptionFacade.insertStandardProductUnitDescriptionWithTx(dto);
	}

	@Override
	public int updateStandardProductUnitDescriptionWithTx(StandardProductUnitDescriptionDTO dto) {
		return standardProductUnitDescriptionFacade.updateStandardProductUnitDescriptionWithTx(dto);
	}

	@Override
	public int deleteStandardProductUnitDescriptionWithTx(StandardProductUnitDescriptionDTO dto) {
		return standardProductUnitDescriptionFacade.deleteStandardProductUnitDescriptionWithTx(dto);
	}


}
	