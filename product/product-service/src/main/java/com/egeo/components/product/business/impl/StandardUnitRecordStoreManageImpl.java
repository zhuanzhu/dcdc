package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.StandardUnitRecordStoreManage;
import com.egeo.components.product.facade.StandardUnitRecordStoreFacade;
import com.egeo.components.product.dto.StandardUnitRecordStoreDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardUnitRecordStore")
public class StandardUnitRecordStoreManageImpl implements StandardUnitRecordStoreManage{

	
	@Resource(name="standardUnitRecordStoreFacade")
	private StandardUnitRecordStoreFacade standardUnitRecordStoreFacade;

	@Override
	public StandardUnitRecordStoreDTO findStandardUnitRecordStoreById(StandardUnitRecordStoreDTO dto) {
		return standardUnitRecordStoreFacade.findStandardUnitRecordStoreById(dto);
	}

	@Override
	public PageResult<StandardUnitRecordStoreDTO> findStandardUnitRecordStoreOfPage(StandardUnitRecordStoreDTO dto, Pagination page) {
		return standardUnitRecordStoreFacade.findStandardUnitRecordStoreOfPage(dto, page);
	}

	@Override
	public List<StandardUnitRecordStoreDTO> findStandardUnitRecordStoreAll(StandardUnitRecordStoreDTO dto) {
		return standardUnitRecordStoreFacade.findStandardUnitRecordStoreAll(dto);
	}

	@Override
	public Long insertStandardUnitRecordStoreWithTx(StandardUnitRecordStoreDTO dto) {
		return standardUnitRecordStoreFacade.insertStandardUnitRecordStoreWithTx(dto);
	}

	@Override
	public int updateStandardUnitRecordStoreWithTx(StandardUnitRecordStoreDTO dto) {
		return standardUnitRecordStoreFacade.updateStandardUnitRecordStoreWithTx(dto);
	}

	@Override
	public int deleteStandardUnitRecordStoreWithTx(StandardUnitRecordStoreDTO dto) {
		return standardUnitRecordStoreFacade.deleteStandardUnitRecordStoreWithTx(dto);
	}


}
	